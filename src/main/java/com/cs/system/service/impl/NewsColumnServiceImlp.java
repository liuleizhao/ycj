package com.cs.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.common.entityenum.ColumnStateEnum;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.NewsColumnDao;
import com.cs.system.entity.NewsColumn;
import com.cs.system.service.NewsColumnService;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class NewsColumnServiceImlp extends BaseServiceSupport<NewsColumn, String> implements NewsColumnService{

	@Autowired
	private NewsColumnDao columnDao;
	
	@Override
	protected BaseDao<NewsColumn, String> getBaseDao() throws Exception {
		return columnDao;
	}
	
	@Override
	public PageInfo<NewsColumn> findColumn(Date startDate, Date endDate, ColumnStateEnum state,Integer page) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addBetweenCriterion("CREATE_DATE BETWEEN ", startDate, endDate);
		sqlCondition.addSingleCriterion("STATE = ", state);
		sqlCondition.addAscOrderbyColumn("ORDER_NUMBER");
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		return findByCondition(sqlCondition, page, pageSize);
	}
	

	@Override
	public List<NewsColumn> findColumn(ColumnStateEnum state) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleCriterion("STATE = ", state);
		sqlCondition.addDescOrderbyColumn("ORDER_NUMBER");
		return findByCondition(sqlCondition);
	}
}
