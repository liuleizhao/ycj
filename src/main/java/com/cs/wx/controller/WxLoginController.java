package com.cs.wx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.common.annotation.AuthValidate;
import com.cs.common.constant.Constant;
import com.cs.common.entityenum.SexEnum;
import com.cs.common.utils.CacheUtil;
import com.cs.common.utils.IpUtil;
import com.cs.mvc.init.InitData;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.Station;
import com.cs.system.entity.WxConfig;
import com.cs.system.entity.WxMenu;
import com.cs.system.entity.WxUser;
import com.cs.system.service.StationService;
import com.cs.system.service.WxUserService;
import com.cs.wx.service.impl.WxServiceImpl;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;

/**
 * 手机端登录控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/wx")
public class WxLoginController extends BaseController{
	
	@Autowired
	private WxServiceImpl wxServiceImpl;
	
	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private StationService stationService;
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param state 检查站code
	 * @return
	 * @throws Exception
	 */
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(HttpServletRequest request,RedirectAttributes model,String stationId) throws Exception{
		
		String key = Constant.WX_YCJ;
		if(StringUtils.isNotBlank(stationId)){
			Station station = InitData.getStation(stationId);
			WxConfig wxConfig = InitData.getWxConfig(stationId);
			if(station != null && wxConfig != null){
				key = stationId;
			}
		}
		String oauthUrl = "http://www.carruyi.com/book/wx/oauth";
		String url = wxServiceImpl.oauth2buildAuthorizationUrl(key,oauthUrl, WxConsts.OAUTH2_SCOPE_USER_INFO,key);
		return redirect(url);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param code 微信授权 code
	 * @param state 检查站 code
	 * @return
	 * @throws Exception
	 */
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/oauth",method = RequestMethod.GET)
	public String oauth(HttpServletRequest request,Model model,String code,String state) throws Exception{
		
		if(StringUtils.isBlank(code) || StringUtils.isBlank(state) ){
			return redirect("/wx/login");
		}
		
		WxOAuth2AccessTokenResult result = wxServiceImpl.oauth2ToGetAccessToken(state,code);
		
		if(result == null){
			return redirect("/wx/login");
		}
		
		WxUserGet userGet = new WxUserGet();
		userGet.setOpenid(result.getOpenid());
		userGet.setLang(WxConsts.LANG_CHINA);
		com.soecode.wxtools.bean.WxUserList.WxUser wxuser =wxServiceImpl.oauth2ToGetUserInfo(result.getAccess_token(), userGet);
		if(wxuser == null){
			return redirect("/wx/login");
		}
		
		Station station = null;
		if(!Constant.WX_YCJ.equals(state)){
			station = InitData.getStation(state);
		}
		
		String ip = IpUtil.getClientIp(request);
		String openId = wxuser.getOpenid();
		WxUser wxUser = wxUserService.findByOpenId(openId);
		if(wxUser==null){
			wxUser = new WxUser();
			wxUser.setOpenId(wxuser.getOpenid());
			wxUser.setSex(SexEnum.findByIndex(wxuser.getSex()));
			wxUser.setNickName(wxuser.getNickname());
			wxUser.setLoginCount(1);
			wxUser.setLastLoginIp(ip);
			wxUser.setLastLoginDate(new Date());
			wxUser.setCreateDate(new Date());
			if(station != null){
				wxUser.setStationId(station.getId());
			}
			wxUserService.insert(wxUser);
		}else{
			wxUser.setSex(SexEnum.findByIndex(wxuser.getSex()));
			wxUser.setNickName(wxuser.getNickname());
			wxUser.setLastLoginDate(new Date());
			wxUser.setLastLoginIp(ip);
			wxUser.setLoginCount(wxUser.getLoginCount()+1);
			wxUserService.updateByPrimaryKey(wxUser);
		}
		
        CacheUtil.setCacheObject(Constant.WX_USER_CACHE, wxUser, request);
        if(station != null){
        	CacheUtil.setCacheObject(Constant.WX_STATION_CACHE, station, request);
        }
        
		return redirect("/wx/index");
	}
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model) throws Exception{
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE,request);
		
		if(station != null){
			String url = "http://www.carruyi.com/book/wx/index";
			ArrayList<String> list = new ArrayList<String>();
			list.add("openLocation");
			WxJsapiConfig wxJsapiConfig = wxServiceImpl.createJsapiConfig(station.getId(),url,list);
			wxJsapiConfig.setAppid(InitData.getWxConfig(station.getId()).getAppId());
			
			List<WxMenu> wxMenuList = stationService.findMenuById(station.getId());
			model.addAttribute("station", station);
			model.addAttribute("wxJsapiConfig", wxJsapiConfig);
			model.addAttribute("wxMenuList", wxMenuList);
		}
		
		return "wx/index";
	}
	
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String test(HttpServletRequest request,Model model,String openId) throws Exception{
		
		WxUser wxUser = wxUserService.findByOpenId(openId);
		Station station = stationService.selectByPrimaryKey(wxUser.getStationId());
		
		CacheUtil.setCacheObject(Constant.WX_USER_CACHE, wxUser, request);
		CacheUtil.setCacheObject(Constant.WX_STATION_CACHE, station, request);
		
		List<WxMenu> wxMenuList = stationService.findMenuById(station.getId());
		
		model.addAttribute("wxMenuList", wxMenuList);
		model.addAttribute("station", station);
		
		return "wx/index";
	}
}
