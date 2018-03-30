package com.cs.system.dao;

import java.util.List;

import com.cs.mvc.dao.BaseDao;
import com.cs.system.entity.VehIsInfo;

public interface VehIsInfDao extends BaseDao<VehIsInfo, String>{
	public List<VehIsInfo> selectBySelective(VehIsInfo vehIsInfo);
}