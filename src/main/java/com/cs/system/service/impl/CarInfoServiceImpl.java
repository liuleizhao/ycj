package com.cs.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.CarInfoDao;
import com.cs.system.entity.CarInfo;
import com.cs.system.service.CarInfoService;

@Service
@Transactional
public class CarInfoServiceImpl extends BaseServiceSupport<CarInfo, String>  implements CarInfoService{

	
	@Autowired
	private CarInfoDao carInfoDao;
	
	@Override
	protected BaseDao<CarInfo, String> getBaseDao() throws Exception {
		return carInfoDao;
	}

	@Override
	public List<CarInfo> findbyUserId(String userId) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("USER_ID =", userId);
		List<CarInfo> carInfoList = carInfoDao.findByCondition(sqlCondition);
		return carInfoList;
	}

}
