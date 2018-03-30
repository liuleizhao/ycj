package com.cs.system.controller;

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
import com.cs.system.entity.Order;
import com.cs.system.service.OrderService;
import com.github.pagehelper.PageInfo;

/**
 * @Description:  订单
 */
@Controller
@RequestMapping(value = "/backend/system/order")
public class OrderController  extends BaseController{
	
	@Autowired
	private OrderService orderService;
	
	private PageInfo<Order> pageView;
	
	/**
	 * @throws Exception
	 * @Description: 订单列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,Order order) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		if(null != order){
			sqlCondition.addLikeCriterion("ORDER_NUMBER like ", order.getOrderNumber());
			sqlCondition.addLikeCriterion("BOOK_NUMBER like ", order.getBookNumber());
			sqlCondition.addLikeCriterion("COURIER_NUMBER like ", order.getCourierNumber());
		}
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = orderService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("order", order);
		return "backend/system/order_list";
	}
	
	/**
	 * @throws Exception
	 * @Description: 订单详情
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String details(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes,String id) throws Exception {

		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数有误");
			return redirect("/backend/system/order/list"); 
		}
		Order order =  orderService.selectByPrimaryKey(id);
		if(null == order){
			redirectAttributes.addFlashAttribute("message", "记录不存在");
			return redirect("/backend/system/order/list"); 
		}
		
		model.addAttribute("order", order);
		return "backend/system/order_details";
	}
	

	/**
	 * @throws Exception
	 * @Description: 发件
	 */
	/*@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String sendUI(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes) throws Exception {
		return "backend/system/order_send";
	}*/
	
	/**
	 * @throws Exception
	 * @Description: 发件
	 */
	/*@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Map<String, String> send(HttpServletRequest request, Model model,String id,String courierNumber) throws Exception {
		if(StringUtils.isBlank(courierNumber)&&StringUtils.isBlank(id)){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "参数有误");
		}
		Order order =  orderService.selectByPrimaryKey(id);
		if(null == order){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "记录不存在");
		}
		if(!OrderStateEnum.BLWC.equals(order.getState())){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "订单状态为"+order.getState().getDescription()+"，不能发货");
		}
		order.setCourierNumber(courierNumber);
		order.setDeliveryDate(new Date());
		order.setState(OrderStateEnum.FZYJ);
		int count = orderService.updateByPrimaryKeySelective(order);
		if(count <= 0){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "操作失败，请重新填写发货信息");
		}
		return InterfaceUtils.buildAjaxMap(courierNumber, InterfaceCodeEnum.SUCCEED, "订单发货成功");
	}*/
	
	
	/**
	 * @throws Exception
	 * @Description: 发件
	 *//*
	@ResponseBody
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public Map<String, String> finish(HttpServletRequest request, Model model,String id) throws Exception {
		if(StringUtils.isBlank(id)){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "参数有误");
		}
		Order order =  orderService.selectByPrimaryKey(id);
		if(null == order){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "记录不存在");
		}
		if(!OrderStateEnum.FZYJ.equals(order.getState())&&!OrderStateEnum.FZLQ.equals(order.getState())){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "订单状态为"+order.getState().getDescription()+"，不能完成订单");
		}
		order.setFinishDate(new Date());
		order.setState(OrderStateEnum.DDWJ);
		int count = orderService.updateByPrimaryKeySelective(order);
		if(count <= 0){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "操作失败");
		}
		return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.SUCCEED, "订单号"+order.getOrderNumber()+"年审办结");
	}*/


}
