package com.cs.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.WxConfigDao;
import com.cs.system.entity.WxConfig;
import com.cs.system.service.WxConfigService;

@Service
@Transactional
public class WxConfigServiceImpl extends
		BaseServiceSupport<WxConfig, String> implements WxConfigService {

	@Autowired
	private WxConfigDao wxConfigDao;

	@Override
	protected BaseDao<WxConfig, String> getBaseDao() throws Exception {
		return wxConfigDao;
	}

	@Override
	public List<WxConfig> findByStationId(List<String> stationIdList) throws Exception{
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleCriterion("STATION_ID IN ", stationIdList);
		return this.findByCondition(sqlCondition);
	}
}
