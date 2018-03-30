package com.cs.system.service.impl;

import java.util.List;

import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.VehicleInfoDao;
import com.cs.system.entity.VehicleInfo;
import com.cs.system.service.VehicleInfoService;

public class VehicleInfoServiceImpl extends BaseServiceSupport<VehicleInfo, String> implements VehicleInfoService {

	private VehicleInfoDao vehicleInfoDao;
	@Override
	protected BaseDao<VehicleInfo, String> getBaseDao() throws Exception {
		return this.vehicleInfoDao;
	}
	@Override
	public List<VehicleInfo> findbyUserId(String userId) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("USER_ID =", userId);
		List<VehicleInfo> vehicleInfos = vehicleInfoDao.findByCondition(sqlCondition);
		return vehicleInfos;
	}

}
