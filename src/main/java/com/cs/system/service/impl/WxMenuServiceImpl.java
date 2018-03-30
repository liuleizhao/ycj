package com.cs.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.WxMenuDao;
import com.cs.system.entity.WxMenu;
import com.cs.system.service.WxMenuService;

@Service
@Transactional
public class WxMenuServiceImpl extends
		BaseServiceSupport<WxMenu, String> implements WxMenuService {

	@Autowired
	private WxMenuDao wxMenuDao;

	@Override
	protected BaseDao<WxMenu, String> getBaseDao() throws Exception {
		return wxMenuDao;
	}
}
