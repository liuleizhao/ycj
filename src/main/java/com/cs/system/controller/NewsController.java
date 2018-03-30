package com.cs.system.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.common.entityenum.HotEnum;
import com.cs.common.entityenum.NewsStateEnum;
import com.cs.common.entityenum.TopEnum;
import com.cs.common.entityenum.UserTypeEnum;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.News;
import com.cs.system.entity.NewsColumn;
import com.cs.system.entity.User;
import com.cs.system.service.NewsColumnService;
import com.cs.system.service.NewsService;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @author xj
 *
 */
@Controller
@RequestMapping(value="/backend/system/news")
public class NewsController extends BaseController{
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsColumnService newsColumnService;
	
	private PageInfo<News> pageView;
	
	
	@RequestMapping(value="/list")
	public String listUI(HttpServletRequest request,Model model,News news) throws Exception{
		
		User user = getBackendUser(request);
		
		SqlCondition sqlCondition = new SqlCondition();
		if(news != null){
			sqlCondition.addLikeCriterion("CODE LIKE", news.getCode());
			sqlCondition.addLikeCriterion("TITLE LIKE ", news.getTitle());
			sqlCondition.addLikeCriterion("TOP = ", news.getTop());
			sqlCondition.addLikeCriterion("HOT = ", news.getHot());
		}
		if(UserTypeEnum.STATION.equals(user.getUserType())){
			sqlCondition.addLikeCriterion("STATION = ", user.getStationId());
		}
		
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = newsService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView",pageView);
		model.addAttribute("news",news);
		modelAddCommon(model);
		
		return "backend/system/news_list";
	}
	
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) throws Exception{
		
		modelAddCommon(model);
		return "backend/system/news";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request,RedirectAttributes redirectAttributes,News news) throws Exception{

		int count = 0;
		
		news.setCreateDate(new Date());
		news.setClickRate(0);
		count = newsService.insert(news);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/news/list");
		}else{
			redirectAttributes.addFlashAttribute("message", "操作失败");
			redirectAttributes.addFlashAttribute("news", news);
			return redirect("/backend/system/news/add");
		}
		
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editUI(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes,String id)throws Exception{

		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数错误");
			return redirect("/backend/system/news/list");
		}
		
		News news = newsService.selectByPrimaryKey(id);
		if(null == news){
			redirectAttributes.addFlashAttribute("message", "该条记录不存在");
			return redirect("/backend/system/news/list");
		}
		
		model.addAttribute("news",news);
		modelAddCommon(model);
		
		return "backend/system/news";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(HttpServletRequest request,RedirectAttributes redirectAttributes,News news)throws Exception{
		
		int count = 0;
		
		count = newsService.updateByPrimaryKeySelective(news);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/news/list");
		}else{
			redirectAttributes.addFlashAttribute("news", news);
			redirectAttributes.addFlashAttribute("message", "操作失败");
			return redirect("/backend/system/news/edit");
		}
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes,String id)throws Exception{
		int count = newsService.deleteByPrimaryKey(id);
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "删除成功");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return redirect("/backend/system/news/list");
	}
	
	private void modelAddCommon(Model model) throws Exception{
		List<HotEnum> hotList = HotEnum.getAll();
		List<NewsStateEnum> newsStateList = NewsStateEnum.getAll();
		List<TopEnum> topList = TopEnum.getAll();
		List<NewsColumn> newsColumnList = newsColumnService.findAllData();
		model.addAttribute("hotList",hotList);
		model.addAttribute("newsStateList",newsStateList);
		model.addAttribute("topList",topList);
		model.addAttribute("newsColumnList",newsColumnList);
		
	}
}
