package com.cs.system.service;

import com.cs.mvc.service.BaseService;
import com.cs.system.entity.Order;
import com.github.pagehelper.PageInfo;

public interface OrderService extends BaseService<Order, String>{
	
	
	//通过微信用户分页查询
	public PageInfo<Order> findByWxUserId(String wxUserId,Integer pageNum,Integer pageSize) throws Exception;
	
	//通过 订单号查询
	public Order findByOrderNumber(String orderNumber) throws Exception;
	
}
