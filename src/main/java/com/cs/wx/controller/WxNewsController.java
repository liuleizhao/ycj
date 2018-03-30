package com.cs.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.common.annotation.AuthValidate;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.News;
import com.cs.system.entity.User;
import com.cs.system.service.NewsService;
import com.github.pagehelper.PageInfo;


/**
 * @author lxy
 *手机端查看新闻资讯
 */

@Controller
@RequestMapping(value = "/wx")
public class WxNewsController extends BaseController{
	@Autowired
	private NewsService newsService;
	
	private PageInfo<News> pageView;
	
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/newsList",method = RequestMethod.GET)
	public String NewsList(HttpServletRequest request,Model model,News news) throws Exception{
		User user = getBackendUser(request);
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addDescOrderbyColumn("TOP");
		pageView = newsService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView",pageView);
		//model.addAttribute("news",news);
		
		return "wx/news_list";
	}
	
	@AuthValidate(exceptAuth = true)
	@RequestMapping(value="/news",method = RequestMethod.GET)
	public String getNews(HttpServletRequest request,Model model,String id) throws Exception{
		News news = newsService.selectByPrimaryKey(id);
		model.addAttribute("news",news);
		return "wx/news";
	}

}
