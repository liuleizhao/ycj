package com.cs.system.service;


import com.cs.mvc.service.BaseService;
import com.cs.system.entity.WxUser;

public interface WxUserService  extends BaseService<WxUser, String>{
	
    
    /**
     * 
     * @param account
     * @return
     * @throws Exception
     */
    public WxUser findByOpenId(String openId) throws Exception;
}
