package com.cs.wx.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.common.constant.Constant;
import com.cs.mvc.init.InitData;
import com.cs.system.vo.TemplateSender;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.PreviewSender;
import com.soecode.wxtools.bean.WxAccessToken;
import com.soecode.wxtools.bean.WxGroupSender;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.WxQrcode;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.IndustryResult;
import com.soecode.wxtools.bean.result.QrCodeResult;
import com.soecode.wxtools.bean.result.SenderResult;
import com.soecode.wxtools.bean.result.TemplateListResult;
import com.soecode.wxtools.bean.result.TemplateResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.bean.result.WxBatchGetMaterialResult;
import com.soecode.wxtools.bean.result.WxCurMenuInfoResult;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.bean.result.WxMaterialCountResult;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.bean.result.WxMenuResult;
import com.soecode.wxtools.bean.result.WxNewsMediaResult;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.bean.result.WxUserGroupResult;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxVideoMediaResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.RandomUtils;
import com.soecode.wxtools.util.crypto.SHA1;
import com.soecode.wxtools.util.file.FileUtils;
import com.soecode.wxtools.util.http.MediaDownloadGetRequestExecutor;
import com.soecode.wxtools.util.http.MediaDownloadPostRequestExecutor;
import com.soecode.wxtools.util.http.MediaUploadRequestExecutor;
import com.soecode.wxtools.util.http.QrCodeDownloadGetRequestExecutor;
import com.soecode.wxtools.util.http.RequestExecutor;
import com.soecode.wxtools.util.http.SimpleGetRequestExecutor;
import com.soecode.wxtools.util.http.SimplePostRequestExecutor;
import com.soecode.wxtools.util.http.URIUtil;
import com.soecode.wxtools.util.http.VideoDownloadPostRequestExecutor;

/**
 * 统一业务处理类
 * @author antgan
 * @date 2016/12/14
 * 
 */
@Service
@Transactional
public class WxServiceImpl{
	//全局的是否正在刷新access token的锁
	protected static final Object globalAccessTokenRefreshLock = new Object();
	//全局的是否正在刷新jsapi_ticket的锁
	protected static final Object globalJsapiTicketRefreshLock = new Object();
	//HttpClient
	protected CloseableHttpClient httpClient;
	
	/**
	 * 构造方法，初始化httpClient
	 */
	public WxServiceImpl() {
		httpClient = HttpClients.createDefault();
	}

	/*****************************
	 *                           *
	 *    以下为微信公众号API接口     *
	 *                           *
	 *****************************/
	
	
	public boolean checkSignature(String signature, String timestamp, String nonce, String echostr) {
		try {
			return SHA1.gen(Constant.WX_TOKEN, timestamp, nonce).equals(signature);
		} catch (Exception e) {
			return false;
		}
	}

	public String getAccessToken(String stationId) throws WxErrorException {
		return getAccessToken(stationId,false);
	}

	public String getAccessToken(String stationId,boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			InitData.getWxConfig(stationId).expireAccessToken();
		}
		if (InitData.getWxConfig(stationId).isAccessTokenExpired()) {
			synchronized (globalAccessTokenRefreshLock) {
				if (InitData.getWxConfig(stationId).isAccessTokenExpired()) {
					String url = WxConsts.URL_GET_ACCESSTOEKN.replace("APPID", InitData.getWxConfig(stationId).getAppId())
							.replace("APPSECRET", InitData.getWxConfig(stationId).getAppSecret());
					try {
						String resultContent = get(url, null);
						WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
						InitData.getWxConfig(stationId).updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
						System.out.println("[wx-tools]update accessToken success. accessToken:"+accessToken.getAccess_token());
					} catch (IOException e) {
						throw new WxErrorException("[wx-tools]refresh accessToken failure.");
					}
				}
			}
		}
		return InitData.getWxConfig(stationId).getAccessToken();
	}

	public String[] getCallbackIp(String stationId) throws WxErrorException {
		String[] ips = null;
		String url = WxConsts.URL_GET_WX_SERVICE_IP.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String responseContent = get(url, null);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(responseContent);
			ips = mapper.readValue(node.get("ip_list"), String[].class);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getCallbackIp failure.");
		}
		return ips;
	}

	
	public String createMenu(String stationId,WxMenu menu, boolean condition) throws WxErrorException {
		String url = null, result = null;
		if (condition)
			url = WxConsts.URL_CREATE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		else
			url = WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", getAccessToken(stationId));

		try {
			result = post(url, menu.toJson());
			System.out.println("[wx-tools]Create Menu result:" + result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createMenu failure.");
		}
		return result;
	}

	
	public String deleteMenu(String stationId) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MENU.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String result = get(url, null);
		System.out.println("[wx-tools]Delete Menu result:" + result);
		return result;
	}

	
	public String deleteMenu(String stationId,String menuid) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", getAccessToken(stationId));

		String json = "{" + "\"menuid\":" + menuid + "}";
		String result = post(url, json);
		System.out.println("[wx-tools]Delete Conditional Menu result:" + result);
		return result;
	}

	
	public WxMenuResult getMenu(String stationId) throws WxErrorException {
		String url = WxConsts.URL_GET_MENU.replace("ACCESS_TOKEN", getAccessToken(stationId));
		WxMenuResult result = null;
		try {
			result = WxMenuResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMenu failure.");
		}
		return result;
	}

	
	public WxCurMenuInfoResult getMenuCurInfo(String stationId) throws WxErrorException {
		String url = WxConsts.URL_GET_CURRENT_MENU_INFO.replace("ACCESS_TOKEN", getAccessToken(stationId));
		WxCurMenuInfoResult result = null;
		try {
			result = WxCurMenuInfoResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMenuCurInfo failure.");
		}
		return result;
	}

	
	public String menuTryMatch(String stationId,String user_id) throws WxErrorException {
		String url = WxConsts.URL_TRYMATCH_MENU.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{" + "\"user_id\":\"" + user_id + "\"" + "}";
		return post(url, json);
	}

	
	public WxMediaUploadResult uploadTempMedia(String stationId,String mediaType, String fileType, InputStream inputStream)
			throws WxErrorException, IOException {
		return uploadTempMedia(stationId,mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
	}

	
	public WxMediaUploadResult uploadTempMedia(String stationId,String mediaType, File file) throws WxErrorException {
		String url = WxConsts.URL_UPLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId)).replace("TYPE", mediaType);
		return execute(new MediaUploadRequestExecutor(), url, file);
	}

	public File downloadTempMedia(String stationId,String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId)).replace("MEDIA_ID",
				media_id);
		return execute(new MediaDownloadGetRequestExecutor(path), url, null);
	}

	
	public WxMediaUploadResult uploadMedia(String stationId,String mediaType, String fileType, InputStream inputStream,
			WxVideoIntroduction introduction) throws WxErrorException, IOException {
		return uploadMedia(stationId,mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType),
				introduction);
	}

	
	public WxMediaUploadResult uploadMedia(String stationId,String mediaType, File file, WxVideoIntroduction introduction)
			throws WxErrorException {
		WxMediaUploadResult result = null;
		String url = WxConsts.URL_UPLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId)).replace("TYPE",
				mediaType);
		// 如果是视频素材，添加视频描述对象
		if (WxConsts.MEDIA_VIDEO.equals(mediaType)) {
			result = execute(new MediaUploadRequestExecutor(introduction), url, file);
		} else {
			result = execute(new MediaUploadRequestExecutor(), url, file);
		}
		return result;
	}

	
	public File downloadMedia(String stationId,String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		return execute(new MediaDownloadPostRequestExecutor(path), url, json);
	}

	
	public WxNewsMediaResult downloadNewsMedia(String stationId,String media_id) throws WxErrorException {
		WxNewsMediaResult newsResult = null;
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		String result = execute(new SimplePostRequestExecutor(), url, json);
		try {
			newsResult = WxNewsMediaResult.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]downloadNewsMedia failure.");
		}
		return newsResult;
	}

	
	public WxVideoMediaResult downloadVideoMedia(String stationId,String media_id,File path) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		return execute(new VideoDownloadPostRequestExecutor(path), url, json);
	}

	
	public WxError deleteMediaMaterial(String stationId,String media_id) throws WxErrorException {
		String url = WxConsts.URL_DELETE_MATERIAL_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{" + "\"media_id\":\"" + media_id + "\"" + "}";
		String result = execute(new SimplePostRequestExecutor(), url, json);
		WxError err = null;
		try {
			err = WxError.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]deleteMediaMaterial failure.");
		}
		return err;
	}

	
	public String addNewsMedia(String stationId,List<WxNewsInfo> news) throws WxErrorException {
		String media_id = null;
		String url = WxConsts.URL_ADD_NEWS_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId));
		ObjectMapper mapper = new ObjectMapper();
		try {
			String arrayJson = mapper.writeValueAsString(news);
			String json = "{\"articles\":" + arrayJson + "}";
			String result = execute(new SimplePostRequestExecutor(), url, json);
			JsonNode node = mapper.readTree(result);
			media_id = node.get("media_id").asText();
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]addNewsMedia failure.");
		}
		return media_id;
	}

	
	public WxMediaUploadResult imageDomainChange(String stationId,File file) throws WxErrorException {
		String url = WxConsts.URL_IMAGE_DOMAIN_CHANGE.replace("ACCESS_TOKEN", getAccessToken(stationId));
		return execute(new MediaUploadRequestExecutor(), url, file);
	}

	
	public WxError updateNewsInfo(String stationId,String media_id, int index, WxNewsInfo newInfo) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_NEWS_MEDIA.replace("ACCESS_TOKEN", getAccessToken(stationId));

		try {
			String json = "{" + "\"media_id\":" + "\"" + media_id + "\"," + "\"index\":" + index + "," + "\"articles\":"
					+ newInfo.toJson() + "}";
			String result = execute(new SimplePostRequestExecutor(), url, json);
			err = WxError.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateNewsInfo failure.");
		}
		return err;
	}

	
	public WxMaterialCountResult getMaterialCount(String stationId) throws WxErrorException {
		String url = WxConsts.URL_GET_MATERIAL_COUNT.replace("ACCESS_TOKEN", getAccessToken(stationId));
		WxMaterialCountResult result = null;
		try {
			result = WxMaterialCountResult.fromJson(get(url, null));
		} catch (Exception e) {
			throw new WxErrorException("[wx-tools]getMaterialCount failure.");
		}
		return result;
	}

	
	public WxBatchGetMaterialResult batchGetMeterial(String stationId,String type, int offset, int count) throws WxErrorException {
		String url = WxConsts.URL_BATCHGET_MATERIAL_MEDIA_LIST.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{" + "\"type\":\"" + type + "\"," + "\"offset\":" + offset + "," + "\"count\":" + count + "}";
		String result = post(url, json);
		WxBatchGetMaterialResult materialResult = null;
		try {
			materialResult = WxBatchGetMaterialResult.fromJson(result);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetMeterial failure.");
		}
		return materialResult;
	}

	
	public WxUserGroupResult createUserGroup(String stationId,String name) throws WxErrorException {
		WxUserGroupResult result = null;
		String url = WxConsts.URL_CREATE_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"group\":{\"name\":\"" + name + "\"}}";
		String postResult = post(url, json);
		try {
			result = WxUserGroupResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createUserGroup failure.");
		}
		return result;
	}

	
	public WxUserGroupResult queryAllUserGroup(String stationId) throws WxErrorException {
		WxUserGroupResult result = null;
		String url = WxConsts.URL_QUERY_ALL_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String getResult = get(url, null);
		try {
			result = WxUserGroupResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]queryAllUserGroup failure.");
		}
		return result;
	}

	
	public int queryGroupIdByOpenId(String stationId,String openid) throws WxErrorException {
		int result = -1;
		String url = WxConsts.URL_QUERY_USER_GROUP_BY_OPENID.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"openid\":\"" + openid + "\"}";
		String postResult = post(url, json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(postResult);
			result = Integer.parseInt(node.get("groupid").toString());
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]queryGroupIdByOpenId failure.");
		}
		return result;
	}

	
	public WxError updateUserGroupName(String stationId,int groupid, String name) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_USER_GROUP_NAME.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"group\":{\"id\":" + groupid + ",\"name\":\"" + name + "\"}}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateUserGroupName failure.");
		}
		return err;
	}

	
	public WxError movingUserToNewGroup(String stationId,String openid, int to_groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_MOVING_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"openid\":\"" + openid + "\",\"to_groupid\":" + to_groupid + "}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]movingUserToNewGroup failure.");
		}
		return err;
	}

	
	public WxError batchMovingUserToNewGroup(String stationId,List<String> openids, int to_groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_BATCH_MOVING_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken(stationId));
		ObjectMapper mapper = new ObjectMapper();
		String arrayJson = null;
		try {
			arrayJson = mapper.writeValueAsString(openids);
			String json = "{\"openid_list\":" + arrayJson + ",\"to_groupid\":" + to_groupid + "}";
			String postResult = post(url, json);
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchMovingUserToNewGroup failure.");
		}
		return err;
	}

	
	public WxError deleteUserGroup(String stationId,int groupid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_DELETE_USER_GROUP.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			String json = "{\"group\":{\"id\":" + groupid + "}}";
			String postResult = post(url, json);
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]deleteUserGroup failure.");
		}
		return err;
	}

	
	public WxError updateUserRemark(String stationId,String openid, String remark) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_UPDATE_USER_REMARK.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"openid\":\"" + openid + "\",\"remark\":\"" + remark + "\"}";
		String postResult = post(url, json);
		try {
			err = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]updateUserRemark failure.");
		}
		return err;
	}

	
	public WxUser getUserInfoByOpenId(String stationId,WxUserGet userGet) throws WxErrorException {
		WxUser user = null;
		String url = WxConsts.URL_GET_USER_INFO.replace("ACCESS_TOKEN", getAccessToken(stationId))
				.replace("OPENID", userGet.getOpenid()).replace("zh_CN", userGet.getLang());
		String postResult = post(url, null);
		try {
			user = WxUser.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getUserInfoByOpenId failure.");
		}
		return user;
	}

	
	public WxUserList batchGetUserInfo(String stationId,List<WxUserGet> usersGet) throws WxErrorException {
		WxUserList list = null;
		String url = WxConsts.URL_BATCH_GET_USER_INFO.replace("ACCESS_TOKEN", getAccessToken(stationId));
		ObjectMapper mapper = new ObjectMapper();
		String arrayJson = null;
		try {
			arrayJson = mapper.writeValueAsString(usersGet);
			String json = "{\"user_list\":" + arrayJson + "}";
			String postResult = post(url, json);
			list = WxUserList.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetUserInfo failure.");
		}
		return list;
	}

	
	public WxUserListResult batchGetUserOpenId(String stationId,String next_openid) throws WxErrorException {
		WxUserListResult result = null;
		String url = WxConsts.URL_BATCH_GET_USER_OPENID.replace("ACCESS_TOKEN", getAccessToken(stationId)).replace("NEXT_OPENID",
				next_openid);
		String getResult = get(url, null);
		try {
			result = WxUserListResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]batchGetUserOpenId failure.");
		}
		return result;
	}

	
	public String oauth2buildAuthorizationUrl(String stationId,String redirectUri, String scope, String state) {
		redirectUri = URIUtil.encodeURIComponent(redirectUri);
		String url = WxConsts.URL_OAUTH2_GET_CODE.replace("APPID", InitData.getWxConfig(stationId).getAppId())
				.replace("REDIRECT_URI", redirectUri).replace("SCOPE", scope).replace("STATE", state);
		return url;
	}

	
	public WxOAuth2AccessTokenResult oauth2ToGetAccessToken(String stationId,String code) throws WxErrorException {
		WxOAuth2AccessTokenResult result = null;
		String url = WxConsts.URL_OAUTH2_GET_ACCESSTOKEN.replace("APPID", InitData.getWxConfig(stationId).getAppId())
				.replace("SECRET", InitData.getWxConfig(stationId).getAppSecret()).replace("CODE", code);
		String getResult = get(url, null);
		try {
			result = WxOAuth2AccessTokenResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetAccessToken failure.");
		}
		return result;
	}

	
	public WxOAuth2AccessTokenResult oauth2ToGetRefreshAccessToken(String stationId,String refresh_token) throws WxErrorException {
		WxOAuth2AccessTokenResult result = null;
		String url = WxConsts.URL_OAUTH2_GET_REFRESH_ACCESSTOKEN.replace("APPID", InitData.getWxConfig(stationId).getAppId())
				.replace("REFRESH_TOKEN", refresh_token);
		String getResult = get(url, null);
		try {
			result = WxOAuth2AccessTokenResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetRefreshAccessToken failure.");
		}
		return result;
	}

	
	public WxUser oauth2ToGetUserInfo(String access_token, WxUserGet userGet) throws WxErrorException {
		WxUser user = null;
		String url = WxConsts.URL_OAUTH2_GET_USER_INFO.replace("ACCESS_TOKEN", access_token)
				.replace("OPENID", userGet.getOpenid()).replace("zh_CN", userGet.getLang());
		String getResult = get(url, null);
		try {
			user = WxUser.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2ToGetUserInfo failure.");
		}
		return user;
	}

	
	public WxError oauth2CheckAccessToken(String access_token, String openid) throws WxErrorException {
		WxError err = null;
		String url = WxConsts.URL_OAUTH2_CHECK_ACCESSTOKEN.replace("ACCESS_TOKEN", access_token).replace("OPENID",
				openid);
		String getResult = get(url, null);
		try {
			err = WxError.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]oauth2CheckAccessToken failure.");
		}
		return err;
	}

	
	public QrCodeResult createQrCode(String stationId,WxQrcode qrcode) throws WxErrorException {
		QrCodeResult result = null;
		String url = WxConsts.URL_GET_QR_CODE.replace("TOKEN", getAccessToken(stationId));
		String json = null;
		try {
			json = qrcode.toJson();
			String postResult = post(url, json);
			result = QrCodeResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]createQrCode failure.");
		}
		return result;
	}

	
	public File downloadQrCode(File dir, String ticket) throws WxErrorException {
		String url = WxConsts.URL_DOWNLOAD_QR_CODE.replace("TICKET", URIUtil.encodeURIComponent(ticket));
		return execute(new QrCodeDownloadGetRequestExecutor(dir), url, null);
	}

	
	public String getShortUrl(String stationId,String long_url) throws WxErrorException {
		String url = WxConsts.URL_LONGURL_TO_SHORTURL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"action\":\"long2short\",\"long_url\":\"" + long_url + "\"}";
		String postResult = post(url, json);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]getShortUrl failure.");
		}
		String shortUrl = node.get("short_url").asText();
		return shortUrl;
	}

	public String getJsapiTicket(String stationId) throws WxErrorException {
		return getJsapiTicket(stationId,false);
	}

	public String getJsapiTicket(String stationId,boolean forceRefresh) throws WxErrorException {
		if (forceRefresh) {
			InitData.getWxConfig(stationId).expireJsapiTicket();
		}
		if (InitData.getWxConfig(stationId).isJsapiTicketExpired()) {
			synchronized (globalJsapiTicketRefreshLock) {
				if (InitData.getWxConfig(stationId).isJsapiTicketExpired()) {
					String url = WxConsts.URL_GET_JS_API_TICKET.replace("ACCESS_TOKEN", getAccessToken(stationId));
					String responseContent = execute(new SimpleGetRequestExecutor(), url, null);
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = null;
					try {
						node = mapper.readTree(responseContent);
						if(node.get("errcode")!=null && !(node.get("errcode").asInt()==0)){
							WxError error = WxError.fromJson(responseContent);
							throw new WxErrorException(error);
						}
						String jsapiTicket = node.get("ticket").asText();
						int expiresInSeconds = node.get("expires_in").asInt();
						InitData.getWxConfig(stationId).updateJsapiTicket(jsapiTicket, expiresInSeconds);
						System.out.println("[wx-tools]update jsapiTicket success. ticket: "+jsapiTicket);
					} catch (Exception e) {
						throw new WxErrorException("[wx-tools]getJsapiTicket failure.");
					}
				}
			}
		}
		return InitData.getWxConfig(stationId).getJsapiTicket();
	}

	public WxJsapiConfig createJsapiConfig(String stationId,String url, List<String> jsApiList) throws WxErrorException {
		long timestamp = System.currentTimeMillis() / 1000;
		String noncestr = RandomUtils.getRandomStr(16);
		String jsapiTicket = getJsapiTicket(stationId);
		try {
			String signature = SHA1.genWithAmple("noncestr="+noncestr,
					"jsapi_ticket="+jsapiTicket,"timestamp="+timestamp,"url="+url);
			WxJsapiConfig jsapiConfig = new WxJsapiConfig();
			jsapiConfig.setTimestamp(timestamp);
			jsapiConfig.setNoncestr(noncestr);
			jsapiConfig.setUrl(url);
			jsapiConfig.setSignature(signature);
			jsapiConfig.setJsApiList(jsApiList);
			return jsapiConfig;
		} catch (NoSuchAlgorithmException e) {
			throw new WxErrorException("[wx-tools]createJsapiConfig failure.");
		}
	}
	
	
	public SenderResult sendAllByGroup(String stationId,WxGroupSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_GROUP_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllByGroup failure.");
		}
		return result;
	}
	
	
	public SenderResult sendAllByOpenid(String stationId,WxOpenidSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_OPENID_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllByOpenid failure.");
		}
		return result;
	}
	
	
	public SenderResult sendAllPreview(String stationId,PreviewSender sender) throws WxErrorException {
		SenderResult result = null;
		String url = WxConsts.URL_PREVIEW_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			String postResult = post(url, sender.toJson());
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllPreview failure.");
		}
		return result;
	}
	
	
	public SenderResult sendAllDelete(String stationId,String msg_id) throws WxErrorException {
		SenderResult result = null;
		String json = "{\"msg_id\":"+msg_id+"}";
		String url = WxConsts.URL_DELETE_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			String postResult = post(url, json);
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllDelete failure.");
		}
		return result;
	}
	
	
	public SenderResult sendAllGetStatus(String stationId,String msg_id) throws WxErrorException {
		SenderResult result = null;
		String json = "{\"msg_id\":\""+msg_id+"\"}";
		String url = WxConsts.URL_GET_STATUS_SEND_ALL.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			String postResult = post(url, json);
			result = SenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]sendAllGetStatus failure.");
		}
		return result;
	}
	
	
	public WxError templateSetIndustry(String stationId,String industry1, String industry2) throws WxErrorException {
		WxError result = null;
		String url = WxConsts.URL_TEMPLATE_SET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"industry_id1\":\""+industry1+"\",\"industry_id2\":\""+industry2+"\"}";
		try {
			String postResult = post(url, json);
			result = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateSetIndustry failure.");
		}
		return result;
	}
	
	
	public IndustryResult templateGetIndustry(String stationId) throws WxErrorException {
		IndustryResult result = null;
		String getResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_INDUSTRY.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			getResult = get(url, null);
			result = IndustryResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetIndustry failure.");
		}
		return result;
	}
	
	
	public TemplateResult templateGetId(String stationId,String template_id_short) throws WxErrorException {
		TemplateResult result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_ID.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"template_id_short\":\""+template_id_short+"\"}";
		try {
			postResult = post(url, json);
			result = TemplateResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetId failure.");
		}
		return result;
	}
	
	
	public TemplateListResult templateGetList(String stationId) throws WxErrorException {
		TemplateListResult result = null;
		String getResult = null;
		String url = WxConsts.URL_TEMPLATE_GET_LIST.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			getResult = get(url, null);
			result = TemplateListResult.fromJson(getResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateGetList failure.");
		}
		return result;
	}
	
	
	public WxError templateDelete(String stationId,String template_id) throws WxErrorException {
		WxError result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_DELETE.replace("ACCESS_TOKEN", getAccessToken(stationId));
		String json = "{\"template_id\":\""+template_id+"\"}";
		try {
			postResult = post(url, json);
			result = WxError.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateDelete failure.");
		}
		return result;
	}
	
	
	public TemplateSenderResult templateSend(String stationId,TemplateSender sender) throws WxErrorException {
		TemplateSenderResult result = null;
		String postResult = null;
		String url = WxConsts.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN", getAccessToken(stationId));
		try {
			postResult = post(url,sender.senderToJSON());
			result = TemplateSenderResult.fromJson(postResult);
		} catch (IOException e) {
			throw new WxErrorException("[wx-tools]templateSend failure.");
		}
		return result;
	}

	protected CloseableHttpClient getHttpclient() {
		return this.httpClient;
	}

	public String get(String url, Map<String, String> params) throws WxErrorException {
		return execute(new SimpleGetRequestExecutor(), url, params);
	}

	public String post(String url, String params) throws WxErrorException {
		return execute(new SimplePostRequestExecutor(), url, params);
	}

	/**
	 * 
	 * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
	 *
	 * @param executor
	 * @param uri
	 * @param data
	 * @return
	 * @throws WxErrorException
	 * @throws IOException 
	 */
	public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException{
		try {
			return executeInternal(executor, uri, data);
		} catch (WxErrorException e) {
			throw e;
		}
	}

	/**
	 * 请求执行器
	 * 
	 * @param executor
	 * @param uri
	 * @param data
	 * @return
	 * @throws WxErrorException
	 * @throws IOException 
	 */
	protected synchronized <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data)
			throws WxErrorException{
		try {
			return executor.execute(getHttpclient(), uri, data);
		} catch (WxErrorException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
