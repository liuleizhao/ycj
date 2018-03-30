package com.cs.system.entity;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.cs.common.entityenum.SexEnum;
import com.cs.mvc.dao.BaseEntity;

public class WxUser extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String userId;

    private String stationId;

    private String openId;

    private String nickName;

    private String phone;

    private SexEnum sex;

    private String province;

    private String city;

    private String country;

    private String headimgUrl;

    private String privilege;

    private String unionId;

    private Date lastLoginDate;

    private String lastLoginIp;

    private Integer loginCount;

    private Date createDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
		private int subscribe;
		private String openid;
		private String nickname;
		private String language;
		private String headimgurl;
		private String subscribe_time;
		private String unionid;
		private String remark;
		private int groupid; 
		private String[] tagid_list;
		

		public String[] getTagid_list() {
			return tagid_list;
		}

		public void setTagid_list(String[] tagid_list) {
			this.tagid_list = tagid_list;
		}

		public int getSubscribe() {
			return subscribe;
		}

		public void setSubscribe(int subscribe) {
			this.subscribe = subscribe;
		}


		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}


		public String getHeadimgurl() {
			return headimgurl;
		}

		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = headimgurl;
		}

		public String getSubscribe_time() {
			return subscribe_time;
		}

		public void setSubscribe_time(String subscribe_time) {
			this.subscribe_time = subscribe_time;
		}

		public String getUnionid() {
			return unionid;
		}

		public void setUnionid(String unionid) {
			this.unionid = unionid;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public int getGroupid() {
			return groupid;
		}

		public void setGroupid(int groupid) {
			this.groupid = groupid;
		}

		public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(this);
		}

		public static WxUser fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, WxUser.class);
		}

	

		@Override
		public String toString() {
			return "WxUser [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
					+ ", language=" + language + ", city=" + city + ", province=" + province + ", country=" + country
					+ ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", unionid=" + unionid
					+ ", remark=" + remark + ", groupid=" + groupid + ", tagid_list=" + Arrays.toString(tagid_list)
					+ ", privilege=" + privilege + "]";
		}



		/**
		 * 获取用户信息参数类
		 * @author antgan
		 *
		 */
		public static class WxUserGet{
			private String openid;
			private String lang;
			
			public WxUserGet() {
				// TODO Auto-generated constructor stub
			}
			
			public WxUserGet(String openid, String lang) {
				super();
				this.openid = openid;
				this.lang = lang;
			}

			public String getOpenid() {
				return openid;
			}
			public void setOpenid(String openid) {
				this.openid = openid;
			}
			public String getLang() {
				return lang;
			}
			public void setLang(String lang) {
				this.lang = lang;
			}
			public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(this);
			}
		}
	
	
	
	
	
	
	
	
	
	
	
}