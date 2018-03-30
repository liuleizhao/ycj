package com.cs.system.service;

import java.util.List;

import com.cs.mvc.service.BaseService;
import com.cs.system.entity.CarInfo;

public interface CarInfoService extends BaseService<CarInfo, String>{
	
	public List<CarInfo> findbyUserId(String userId) throws Exception;
	
}
