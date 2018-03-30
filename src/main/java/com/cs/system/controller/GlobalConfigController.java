package com.cs.system.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.GlobalConfig;
import com.cs.system.service.GlobalConfigService;
import com.github.pagehelper.PageInfo;
/**
 * 
 */
@Controller
@RequestMapping(value="/backend/system/globalConfig")
public class GlobalConfigController extends BaseController{
	
	@Autowired
	private GlobalConfigService globalConfigService; 
	
	private PageInfo<GlobalConfig> pageView;
	
	
	@RequestMapping(value="/list")
	public String listUI(HttpServletRequest request,Model model,GlobalConfig globalConfig) throws Exception{
		
		
		SqlCondition sqlCondition = new SqlCondition();
		if(globalConfig != null){
			sqlCondition.addLikeCriterion("NAME LIKE ", globalConfig.getName());
			sqlCondition.addLikeCriterion("DATA_KEY LIKE ", globalConfig.getDataKey());
		}
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = globalConfigService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView",pageView);
		model.addAttribute("globalConfig",globalConfig);
		
		return "backend/system/global_config_list";
	}
	
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model) throws Exception{
		
		return "backend/system/global_config";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request,RedirectAttributes redirectAttributes,GlobalConfig globalConfig) throws Exception{

		int count = 0;
		
		globalConfig.setCreateDate(new Date());
		count = globalConfigService.insert(globalConfig);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/globalConfig/list");
		}else{
			redirectAttributes.addFlashAttribute("message", "操作失败");
			redirectAttributes.addFlashAttribute("globalConfig", globalConfig);
			return redirect("/backend/system/globalConfig/add");
		}
		
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editUI(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes,String id)throws Exception{

		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数错误");
			return redirect("/backend/system/globalConfig/list");
		}
		
		GlobalConfig globalConfig = globalConfigService.selectByPrimaryKey(id);
		if(null == globalConfig){
			redirectAttributes.addFlashAttribute("message", "该条记录不存在");
			return redirect("/backend/system/globalConfig/list");
		}
		
		model.addAttribute("globalConfig",globalConfig);
		
		return "backend/system/global_config";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(HttpServletRequest request,RedirectAttributes redirectAttributes,GlobalConfig globalConfig)throws Exception{
		
		int count = 0;
		
		count = globalConfigService.updateByPrimaryKeySelective(globalConfig);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/globalConfig/list");
		}else{
			redirectAttributes.addFlashAttribute("globalConfig", globalConfig);
			redirectAttributes.addFlashAttribute("message", "操作失败");
			return redirect("/backend/system/globalConfig/edit");
		}
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes,String id)throws Exception{
		int count = globalConfigService.deleteByPrimaryKey(id);
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "删除成功");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return redirect("/backend/system/globalConfig/list");
	}
	
}
