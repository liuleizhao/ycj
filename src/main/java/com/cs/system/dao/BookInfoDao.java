package com.cs.system.dao;

import com.cs.mvc.dao.BaseDao;
import com.cs.system.entity.BookInfo;

public interface BookInfoDao extends BaseDao<BookInfo,String>{

	Integer updateStateByBookNumber(Integer state,String bookNumber);
}