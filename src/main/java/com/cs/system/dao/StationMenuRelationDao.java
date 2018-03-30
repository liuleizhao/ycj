package com.cs.system.dao;

import org.apache.ibatis.annotations.Param;

import com.cs.mvc.dao.BaseDao;
import com.cs.system.entity.StationMenuRelation;

public interface StationMenuRelationDao extends BaseDao<StationMenuRelation,String> {
  
	public int inserts(@Param("stationId") String stationId,@Param("menuIds") String[] menuIds);
	
	public int deletes(@Param("stationId") String stationId,@Param("menuIds") String[] menuIds);
}