package com.cs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.VehIsInfDao;
import com.cs.system.entity.VehIsInfo;
import com.cs.system.service.VehIsInfoService;

@Service
@Transactional
public class VehIsInfoServiceImpl extends BaseServiceSupport<VehIsInfo, String> implements VehIsInfoService {
	@Autowired
	private VehIsInfDao vehIsInfoDao;

	
	@Override
	protected BaseDao<VehIsInfo, String> getBaseDao() throws Exception {
		return vehIsInfoDao;
	}

	

}
