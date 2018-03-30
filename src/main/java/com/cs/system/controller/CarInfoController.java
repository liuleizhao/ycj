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
import com.cs.system.entity.CarInfo;
import com.cs.system.service.CarInfoService;
import com.github.pagehelper.PageInfo;

/**
 * 
 */
@Controller
@RequestMapping(value = "/backend/system/carInfo")
public class CarInfoController  extends BaseController{
	
	@Autowired
	private CarInfoService carInfoService;
	
	private PageInfo<CarInfo> pageView;
	
	/**
	 * @throws Exception
	 * @Description: 预约信息列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,CarInfo carInfo) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		if(null != carInfo){
			sqlCondition.addLikeCriterion("PLAT_NUMBER like ", carInfo.getPlatNumber());
			sqlCondition.addLikeCriterion("FRAME_NUMBER like ", carInfo.getFrameNumber());
		}
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = carInfoService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("carInfo", carInfo);
		return "backend/system/car_info_list";
	}
	
	/**
	 * @throws Exception
	 * @Description: 车辆信息详情
	 */
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String info(HttpServletRequest request,RedirectAttributes redirectAttributes, Model model,String id) throws Exception {

		if(StringUtils.isBlank(id)){
			redirectAttributes.addFlashAttribute("message", "参数有误");
			return redirect("/backend/system/carInfo/list"); 
		}
		
		CarInfo carInfo =  carInfoService.selectByPrimaryKey(id);
		if(null == carInfo){
			redirectAttributes.addFlashAttribute("message", "记录不存在");
			return redirect("/backend/system/carInfo/list"); 
		}
		
		model.addAttribute("carInfo", carInfo);
		return "backend/system/car_info_details";
	}

}
