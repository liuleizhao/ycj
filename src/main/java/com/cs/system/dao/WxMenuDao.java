package com.cs.system.dao;

import java.util.List;

import com.cs.mvc.dao.BaseDao;
import com.cs.system.entity.WxMenu;

public interface WxMenuDao extends BaseDao<WxMenu,String>{

	public List<WxMenu> findMenuByStationId(String stationId);
}