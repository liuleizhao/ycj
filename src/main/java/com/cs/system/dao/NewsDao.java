package com.cs.system.dao;

import com.cs.mvc.dao.BaseDao;
import com.cs.system.entity.News;

public interface NewsDao extends BaseDao<News, String>{
 
	
	
	public void findNews(String stationId,String code,String title);
	
}