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

import com.cs.common.entityenum.CommonStateEnum;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.NewsColumn;
import com.cs.system.service.NewsColumnService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/backend/system/newsColumn")
public class NewsColumnController extends BaseController{
	
	@Autowired
	private NewsColumnService newsColumnService;

	private PageInfo<NewsColumn> pageView;
	
	
	
	@RequestMapping(value="/list")
	public String listUI(HttpServletRequest request,Model model,NewsColumn newsColumn)throws Exception{
		SqlCondition sqlCondition = new SqlCondition();
		if(newsColumn != null){
			sqlCondition.addLikeCriterion("NAME LIKE ", newsColumn.getName());
			sqlCondition.addLikeCriterion("STATE = ", newsColumn.getState());
		}
		sqlCondition.addAscOrderbyColumn("ORDER_NUMBER");
		pageView = newsColumnService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("NewsColumn", newsColumn);
		
		
		modelAddCommon(model);
		
		return "backend/system/news_column_list";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) throws Exception{
		
		modelAddCommon(model);
		return "backend/system/news_column";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request,RedirectAttributes redirectAttributes,NewsColumn newsColumn) throws Exception{

		int count = 0;
		newsColumn.setCreator(getBackendUser(request).getAccount());
		newsColumn.setCreateDate(new Date());
		count = newsColumnService.insert(newsColumn);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/newsColumn/list");
		}else{
			redirectAttributes.addFlashAttribute("message", "操作失败");
			redirectAttributes.addFlashAttribute("newsColumn", newsColumn);
			return redirect("/backend/system/newsColumn/add");
		}
		
	}
	
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editUI(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes,String id) throws Exception{
		
		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数错误");
			return redirect("/backend/system/newsColumn/list");
		}
		
		NewsColumn newsColumn = newsColumnService.selectByPrimaryKey(id);
		if(null == newsColumn){
			redirectAttributes.addFlashAttribute("message", "该条记录不存在");
			return redirect("/backend/system/newsColumn/list");
		}
		model.addAttribute("newsColumn",newsColumn);

		modelAddCommon(model);
		return "backend/system/news_column";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(HttpServletRequest request,RedirectAttributes redirectAttributes,NewsColumn newsColumn) throws Exception{

		int count = 0;
		
		count = newsColumnService.updateByPrimaryKeySelective(newsColumn);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/newsColumn/list");
		}else{
			redirectAttributes.addFlashAttribute("message", "操作失败");
			redirectAttributes.addFlashAttribute("newsColumn", newsColumn);
			return redirect("/backend/system/newsColumn/edit");
		}
		
	}
	
	
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes,String id)throws Exception{

		int count = newsColumnService.deleteByPrimaryKey(id);
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "删除成功");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return redirect("/backend/system/newsColumn/list");
		
	}
	
	private void modelAddCommon(Model model) throws Exception{
		
		List<CommonStateEnum> stateList = CommonStateEnum.getAll();
		
		model.addAttribute("stateList",stateList);

	}
	
}
