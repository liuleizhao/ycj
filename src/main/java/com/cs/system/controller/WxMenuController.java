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
import com.cs.system.entity.WxMenu;
import com.cs.system.service.WxMenuService;
import com.github.pagehelper.PageInfo;
/**
 * 公众号微平台主页菜单控制器
 */
@Controller
@RequestMapping(value="/backend/system/wxMenu")
public class WxMenuController extends BaseController{
	
	@Autowired
	private WxMenuService wxMenuService;
	
	private PageInfo<WxMenu> pageView;
	
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(HttpServletRequest request,Model model,WxMenu wxMenu) throws Exception{
		
		SqlCondition sqlCondition = new SqlCondition();
		if(wxMenu != null){
			sqlCondition.addLikeCriterion("NAME LIKE ", wxMenu.getName());
		}
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = wxMenuService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView",pageView);
		model.addAttribute("wxMenu",wxMenu);
		
		return "backend/system/wx_menu_list";
	}
	
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model) throws Exception{
		
		return "backend/system/wx_menu";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request,RedirectAttributes redirectAttributes,WxMenu wxMenu) throws Exception{

		int count = 0;
		
		wxMenu.setCreateDate(new Date());
		count = wxMenuService.insert(wxMenu);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/wxMenu/list");
		}else{
			redirectAttributes.addFlashAttribute("message", "操作失败");
			redirectAttributes.addFlashAttribute("wxMenu", wxMenu);
			return redirect("/backend/system/wxMenu/add");
		}
		
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editUI(HttpServletRequest request,Model model,RedirectAttributes redirectAttributes,String id)throws Exception{

		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数错误");
			return redirect("/backend/system/wxMenu/list");
		}
		
		WxMenu wxMenu = wxMenuService.selectByPrimaryKey(id);
		if(null == wxMenu){
			redirectAttributes.addFlashAttribute("message", "该条记录不存在");
			return redirect("/backend/system/wxMenu/list");
		}
		
		model.addAttribute("wxMenu",wxMenu);
		
		return "backend/system/wx_menu";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(HttpServletRequest request,RedirectAttributes redirectAttributes,WxMenu wxMenu)throws Exception{
		
		int count = 0;
		
		count = wxMenuService.updateByPrimaryKeySelective(wxMenu);
		
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "操作成功");
			return redirect("/backend/system/wxMenu/list");
		}else{
			redirectAttributes.addFlashAttribute("wxMenu", wxMenu);
			redirectAttributes.addFlashAttribute("message", "操作失败");
			return redirect("/backend/system/wxMenu/edit");
		}
	}
	
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes,String id)throws Exception{
		int count = wxMenuService.deleteByPrimaryKey(id);
		if(count>0){
			redirectAttributes.addFlashAttribute("message", "删除成功");
		}else{
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return redirect("/backend/system/wxMenu/list");
	}
	
}
