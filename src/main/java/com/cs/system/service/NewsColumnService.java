package com.cs.system.service;

import java.util.Date;
import java.util.List;

import com.cs.common.entityenum.ColumnStateEnum;
import com.cs.mvc.service.BaseService;
import com.cs.system.entity.NewsColumn;
import com.github.pagehelper.PageInfo;

public interface NewsColumnService extends BaseService<NewsColumn, String>{

	/**
	 * 后台 栏目管理页面获取栏目列表
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param state 栏目状态
	 * @param page 页码
	 * @return
	 * @throws Exception
	 */
	public PageInfo<NewsColumn> findColumn(Date startDate, Date endDate, ColumnStateEnum state,Integer page) throws Exception;
	
	/**
	 * 
	 * @param state 栏目状态
	 * @return
	 * @throws Exception
	 */
	public List<NewsColumn> findColumn(ColumnStateEnum state) throws Exception;

	
}
