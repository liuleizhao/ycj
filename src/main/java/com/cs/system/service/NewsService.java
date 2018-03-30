package com.cs.system.service;

import java.util.Date;
import java.util.List;

import com.cs.common.entityenum.NewsStateEnum;
import com.cs.common.entityenum.TopEnum;
import com.cs.mvc.service.BaseService;
import com.cs.system.entity.News;
import com.github.pagehelper.PageInfo;

public interface NewsService extends BaseService<News, String>{

	/**
	 * 后台新闻管理页面 获取新闻列表
	 * @param columnId 栏目id	
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param state 状态
	 * @param top 置顶
	 * @param page 页码
	 * @return
	 * @throws Exception
	 */
	public PageInfo<News> findNews(String columnId,Date startDate,Date endDate,NewsStateEnum state,TopEnum top,Integer page) throws Exception;

	/**
	 * 前端新闻页面 获取新闻
	 * @param columnId 栏目id
	 * @param page 页码
	 * @param size 每页条数
	 * @return
	 * @throws Exception
	 */
	public PageInfo<News> findNews(String columnId,Integer page,Integer size) throws Exception;
	
	/**
	 * 前端首页获取新闻
	 * @param columnId
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<News> findNews(String columnId,Integer size) throws Exception;
	
	/**
	 * 获取新闻
	 * @param code 新闻code码
	 * @return
	 * @throws Exception
	 */
	public News findNews(Integer code) throws Exception;
	
}
