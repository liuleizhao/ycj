package com.cs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.init.InitData;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.GlobalConfigDao;
import com.cs.system.entity.GlobalConfig;
import com.cs.system.service.GlobalConfigService;

@Service
@Transactional
public class GlobalConfigServiceImpl extends
		BaseServiceSupport<GlobalConfig, String> implements GlobalConfigService {
	
	@Autowired
	private GlobalConfigDao globalConfigDao;
	@Autowired
	private InitData initData;

	@Override
	protected BaseDao<GlobalConfig, String> getBaseDao() throws Exception {
		return globalConfigDao;
	}

	@Override
	public GlobalConfig findByDataKey(String dataKey) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("DATA_KEY = ", dataKey);
		return this.findUniqueByCondition(sqlCondition);
	}

	@Override
	public int deleteByPrimaryKey(String id) throws Exception {
		int result = super.deleteByPrimaryKey(id);
		initData.reloadGlobalConfig();
		return result;
	}

	@Override
	public int insert(GlobalConfig record) throws Exception {
		int result = super.insert(record);
		initData.reloadGlobalConfig();
		return result;
	}

	@Override
	public int insertSelective(GlobalConfig record) throws Exception {
		int result = super.insertSelective(record);
		initData.reloadGlobalConfig();
		return result;
	}

	@Override
	public int updateByPrimaryKeySelective(GlobalConfig record)
			throws Exception {
		int result = super.updateByPrimaryKeySelective(record);
		initData.reloadGlobalConfig();
		return result;
	}

	@Override
	public int updateByPrimaryKey(GlobalConfig record) throws Exception {
		int result = super.updateByPrimaryKey(record);
		initData.reloadGlobalConfig();
		return result;
	}
	
	

}
