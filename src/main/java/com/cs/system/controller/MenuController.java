package com.cs.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cs.mvc.web.BaseController;
import com.cs.wx.service.impl.WxServiceImpl;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.result.WxMenuResult;

/**
 * @author lxy
 *菜单创建，修改，删除，查看
 */

@Controller
@RequestMapping(value = "/app/menu")
public class MenuController extends BaseController{
	
	@Autowired
	WxServiceImpl WxServiceImpl;
	
	String stationId;
	WxMenu menu;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,Model model) throws Exception{
		WxMenuResult menu= WxServiceImpl.getMenu(stationId);
		model.addAttribute("menu", menu);
		return "";
	}
	@RequestMapping(value="/delete")
	public String deleteMenu(HttpServletRequest request,Model model) throws Exception{
		String result= WxServiceImpl.deleteMenu(stationId);
		model.addAttribute("result", result);
		return "";
	}
	
	@RequestMapping(value="/create")
	public String createMenu(HttpServletRequest request,Model model) throws Exception{
		String result= WxServiceImpl.createMenu(stationId,menu,false);
		model.addAttribute("result", result);
		return "";
	}
	
	@RequestMapping(value="/edit")
	public String editMenu(HttpServletRequest request,Model model) throws Exception{
		String result= WxServiceImpl.deleteMenu(stationId);
		result= WxServiceImpl.createMenu(stationId,menu,false);
		model.addAttribute("result", result);
		return "";
	}

}
