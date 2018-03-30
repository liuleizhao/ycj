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
import com.cs.system.entity.BookInfo;
import com.cs.system.service.BookInfoService;
import com.github.pagehelper.PageInfo;

/**
 * 
 */
@Controller
@RequestMapping(value = "/backend/system/bookInfo")
public class BookInfoController  extends BaseController{
	
	@Autowired
	private BookInfoService bookInfoService;
	
	private PageInfo<BookInfo> pageView;
	
	/**
	 * @throws Exception
	 * @Description: 预约信息列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,BookInfo bookInfo) throws Exception {
		
		SqlCondition sqlCondition = new SqlCondition();
		if(bookInfo != null){
			sqlCondition.addLikeCriterion("BOOK_NUMBER like ", bookInfo.getBookNumber());
			sqlCondition.addLikeCriterion("BOOKER_NAME like ", bookInfo.getBookerName());
			sqlCondition.addSingleCriterion("BOOK_DATE >= ", bookInfo.getsDate());
			sqlCondition.addSingleCriterion("BOOK_DATE <= ", bookInfo.geteDate());
		}
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = bookInfoService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("bookInfo", bookInfo);
		return "backend/system/book_info_list";
	}
	
	/**
	 * @throws Exception
	 * @Description: 预约信息详情
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String info(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes,String id) throws Exception {
		
		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数有误");
			return redirect("/backend/system/bookInfo/list"); 
		}
		
		BookInfo bookInfo = bookInfoService.selectByPrimaryKey(id);
		if(bookInfo == null){
			redirectAttributes.addFlashAttribute("message", "记录不存在");
			return redirect("/backend/system/bookInfo/list"); 
		}
		
		model.addAttribute("bookInfo", bookInfo);
		return "backend/system/book_info_details";
	}


}
