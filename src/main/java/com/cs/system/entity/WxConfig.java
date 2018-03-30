package com.cs.system.entity;

import java.util.Date;

import com.cs.mvc.dao.BaseEntity;
import com.soecode.wxtools.bean.WxAccessToken;

public class WxConfig extends BaseEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stationId;

    private String appId;

    private String appSecret;

    private String token;

    private String aesKey;

    private String mchId;

    private String apiKey;

    private Date createDate;
    
    //内存更新
  	private volatile String accessToken;
  	private volatile long expiresTime;
  	private volatile String jsapiTicket;
  	private volatile long jsapiTicketExpiresTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

  	
  	public long getExpiresTime() {
  		return expiresTime;
  	}

  	public void setExpiresTime(long expiresTime) {
  		this.expiresTime = expiresTime;
  	}
  	
  	
  	public String getAccessToken() {
  		return accessToken;
  	}
  	
  	public boolean isAccessTokenExpired() {
  		return System.currentTimeMillis() > this.expiresTime;
  	}

  	public void expireAccessToken() {
  		this.expiresTime = 0;
  	}

  	public synchronized void updateAccessToken(WxAccessToken accessToken) {
  		updateAccessToken(accessToken.getAccess_token(), accessToken.getExpires_in());
  	}

  	public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
  		this.accessToken = accessToken;
  		this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
  	}

  	public String getJsapiTicket() {
  		return jsapiTicket;
  	}

  	public void setJsapiTicket(String jsapiTicket) {
  		this.jsapiTicket = jsapiTicket;
  	}

  	public long getJsapiTicketExpiresTime() {
  		return jsapiTicketExpiresTime;
  	}

  	public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
  		this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
  	}

  	public boolean isJsapiTicketExpired() {
  		return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
  	}

  	public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
  		this.jsapiTicket = jsapiTicket;
  		// 预留200秒的时间
  		this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
  	}
  	
  	public void expireJsapiTicket() {
  		this.jsapiTicketExpiresTime = 0;
  	}
  
  	@Override
  	public String toString() {
  		return "WxConfig [appId=" + appId + ", appSecret=" + appSecret + ", token=" + token + ", aesKey=" + aesKey
  				+ ", mchId=" + mchId + ", apiKey=" + apiKey + ", accessToken=" + accessToken + ", expiresTime="
  				+ expiresTime + ", jsapiTicket=" + jsapiTicket + ", jsapiTicketExpiresTime=" + jsapiTicketExpiresTime
  				+ "]";
  	}
}