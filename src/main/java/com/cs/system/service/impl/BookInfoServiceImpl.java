package com.cs.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.common.entityenum.BookStateEnum;
import com.cs.mvc.dao.BaseDao;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.service.BaseServiceSupport;
import com.cs.system.dao.BookInfoDao;
import com.cs.system.entity.BookInfo;
import com.cs.system.service.BookInfoService;

@Service
@Transactional
public class BookInfoServiceImpl extends BaseServiceSupport<BookInfo, String>  implements BookInfoService{

	
	@Autowired
	private BookInfoDao bookInfoDao;
	
	@Override
	protected BaseDao<BookInfo, String> getBaseDao() throws Exception {
		return bookInfoDao;
	}

	@Override
	public List<BookInfo> findbyUserId(String userId) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("USER_ID =", userId);
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		List<BookInfo> bookInfoList = bookInfoDao.findByCondition(sqlCondition);
		return bookInfoList;
	}

	@Override
	public Integer cancel(String bookInfoId) throws Exception {
		BookInfo bookInfo = new BookInfo();
		bookInfo.setId(bookInfoId);
		bookInfo.setBookState(BookStateEnum.YYQX);
		return bookInfoDao.updateByPrimaryKeySelective(bookInfo);
	}

	@Override
	public List<BookInfo> findbyState(BookStateEnum state) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("BOOK_STATE =", state.getId());
		List<BookInfo> bookInfoList = bookInfoDao.findByCondition(sqlCondition);
		return bookInfoList;
	}

	@Override
	public void updateState(List<Map<String, Object>> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			Integer bookState = (Integer) list.get(i).get("bookState");
			if(!bookState.equals(BookStateEnum.YYZ.getId())){
				String bookNumber = (String) list.get(i).get("bookNumber");
				bookInfoDao.updateStateByBookNumber(bookState,bookNumber);
			}
		}
	}

	@Override
	public BookInfo findByBookNumber(String bookNumber) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		sqlCondition.addSingleNotNullCriterion("BOOK_NUMBER =", bookNumber);
		List<BookInfo> bookInfoList = bookInfoDao.findByCondition(sqlCondition);
		BookInfo bookInfo = null;
		if(bookInfoList.size()>0){
			bookInfo = bookInfoList.get(0);
		}
		return bookInfo;
	}
	
}
