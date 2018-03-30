package com.cs.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.common.constant.Constant;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.mvc.init.InitData;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.Order;
import com.cs.system.entity.WxUser;
import com.cs.system.service.OrderService;
import com.github.pagehelper.PageInfo;

/**
 * 手机端登录控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/wx/order")
public class WxOrderController extends BaseController{
	
	@Autowired
	private OrderService orderService;
	
	private PageInfo<Order> pageView;
	
	/**
	 * @throws Exception
	 * @Description: 订单列表
	 */
	@RequestMapping(value = "/list")
	public String listUI(HttpServletRequest request, Model model) throws Exception {
		
		WxUser wxUser = getAppUser(request);
		
		pageView = orderService.findByWxUserId(wxUser.getId(),getCurrentPage(request),Constant.WX_PAGE);
		
		model.addAttribute("pageView", pageView);
		
		return "/wx/order_list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxList")
	public Map<String,Object> list(HttpServletRequest request, Model model) throws Exception {
		
		WxUser wxUser = getAppUser(request);
		
		pageView = orderService.findByWxUserId(wxUser.getId(),getCurrentPage(request),Constant.WX_PAGE);
		
		return InterfaceUtils.buildAjaxMap(pageView.getList(), InterfaceCodeEnum.SUCCEED, message,getCurrentPage(request));
	}
	
}
