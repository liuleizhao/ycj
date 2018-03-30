package com.cs.system.service;

import java.util.List;
import java.util.Map;

import com.cs.common.entityenum.BookStateEnum;
import com.cs.mvc.service.BaseService;
import com.cs.system.entity.BookInfo;

public interface BookInfoService extends BaseService<BookInfo, String>{
	
	public List<BookInfo> findbyUserId(String userId) throws Exception;
	
	public List<BookInfo> findbyState(BookStateEnum state) throws Exception;
	
	public Integer cancel(String bookInfoId) throws Exception;
	
	public void updateState(List<Map<String, Object>> list) throws Exception;
	
	public BookInfo findByBookNumber(String bookNumber) throws Exception;
	
}
