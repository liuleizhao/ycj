package com.cs.system.service;

import java.util.List;

import com.cs.mvc.service.BaseService;
import com.cs.system.entity.VehicleInfo;

public interface VehicleInfoService extends BaseService<VehicleInfo, String>{
	public List<VehicleInfo> findbyUserId(String userId) throws Exception;
}
