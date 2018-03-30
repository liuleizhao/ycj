package com.cs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.common.entityenum.OrderStateEnum;
import com.cs.common.utils.json.JSONArray;
import com.cs.common.utils.json.JSONObject;
import com.cs.common.utils.json.XML;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.OrderDao;
import com.cs.system.entity.Order;
import com.cs.system.service.OrderService;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class OrderServiceImpl extends BaseServiceSupport<Order, String>  implements OrderService{

	
	@Autowired
	private OrderDao orderDao;

	@Override
	protected BaseDao<Order, String> getBaseDao() throws Exception {
		return orderDao;
	}
	
	@Override
	public Order findByOrderNumber(String orderNumber) throws Exception {
		
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("ORDER_NUMBER = ", orderNumber);
		
		return this.findUniqueByCondition(sqlCondition);
	}

	@Override
	public PageInfo<Order> findByWxUserId(String wxUserId,Integer pageNum,Integer pageSize) throws Exception {
		
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("WX_USER_ID = ", wxUserId);
		sqlCondition.addSingleNotNullCriterion("STATE <> ", OrderStateEnum.FQ);
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		return this.findByCondition(sqlCondition, pageNum, pageSize);
		
	}

}
