package com.cs.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.common.entityenum.HotEnum;
import com.cs.common.entityenum.NewsStateEnum;
import com.cs.common.entityenum.TopEnum;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.NewsDao;
import com.cs.system.entity.News;
import com.cs.system.service.NewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class NewsServiceImlp extends BaseServiceSupport<News, String> implements NewsService{

	@Autowired
	private NewsDao newsDao;
	
	@Override
	protected BaseDao<News, String> getBaseDao() throws Exception {
		return newsDao;
	}
	
	@Override
	public PageInfo<News> findNews(String columnId, Date startDate, Date endDate, NewsStateEnum state,TopEnum top,Integer page) throws Exception{
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleCriterion("COLUMN_ID = ", columnId);
		sqlCondition.addBetweenCriterion("CREATE_DATE BETWEEN ", startDate, endDate);
		sqlCondition.addSingleCriterion("STATE = ", state);
		sqlCondition.addSingleCriterion("TOP = ", top);
		sqlCondition.addAscOrderbyColumn("CODE");
		return findByCondition(sqlCondition, page, pageSize);
	}
	
	@Override
	public PageInfo<News> findNews(String columnId,Integer page,Integer size) throws Exception{
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleCriterion("COLUMN_ID = ", columnId);
		sqlCondition.addSingleCriterion("STATE = ", NewsStateEnum.VALID);
		sqlCondition.addDescOrderbyColumn("TOP");
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		return findByCondition(sqlCondition, page, size);
	}

	@Override
	public List<News> findNews(String columnId,Integer size) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleCriterion("COLUMN_ID = ", columnId);
		sqlCondition.addSingleCriterion("STATE = ", NewsStateEnum.VALID);
		sqlCondition.addDescOrderbyColumn("TOP");
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		sqlCondition.addSingleCriterion("ROWNUM <= ", size);
		return findByCondition(sqlCondition);
	}


	@Override
	public News findNews(Integer code) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleCriterion("CODE = ", code);
		sqlCondition.addSingleCriterion("STATE = ", NewsStateEnum.VALID);
		return findByCondition(sqlCondition).get(0);
	}

	/*if(pageNum ==null || pageNum <1){
		pageNum = this.pageNum;
	}
	
	if(pageSize == null || pageSize <1){
		pageSize = this.pageSize;
	}
	PageHelper.startPage(pageNum, pageSize);*/
}
