package com.cs.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.common.entityenum.UserTypeEnum;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.init.InitData;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.CarInfo;
import com.cs.system.entity.Station;
import com.cs.system.entity.User;
import com.cs.system.entity.WxUser;
import com.cs.system.service.CarInfoService;
import com.cs.system.service.WxUserService;
import com.github.pagehelper.PageInfo;
/**
 * 微信用户控制器
 */
@Controller
@RequestMapping(value = "/backend/system/wxUser")
public class WxUserController extends BaseController{
	
	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private CarInfoService carInfoService;
	
	private PageInfo<WxUser> pageView;
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request,
			@ModelAttribute("wxuser") WxUser wxuser,Model model) throws Exception {
		
		User user = getBackendUser(request);
		
		SqlCondition sqlCondition = new SqlCondition();
		if(wxuser != null){
			sqlCondition.addLikeCriterion("NICK_NAME LIKE ", wxuser.getNickName());
			sqlCondition.addSingleCriterion("STATION_ID = ", wxuser.getStationId());
		}
		
		if(user.getUserType().equals(UserTypeEnum.STATION)){
			sqlCondition.addSingleNotNullCriterion("STATION_ID = ", user.getStationId());
		}else{
			List<Station> stationList = InitData.getStationList();
			model.addAttribute("stationList", stationList);
		}
		
		sqlCondition.addDescOrderbyColumn("CREATE_DATE");
		pageView = wxUserService.findByCondition(sqlCondition,getCurrentPage(request),12);
		
		model.addAttribute("pageView", pageView);
		return "backend/system/wx_user_list";
		
	}
	@RequestMapping(value = "/carInfo", method=RequestMethod.GET)
	public String info(HttpServletRequest request,Model model,String wxuserId){
		try {
			List<CarInfo> carInfos = carInfoService.findbyUserId(wxuserId);
			model.addAttribute("carInfos", carInfos);
		} catch (Exception e) {
			model.addAttribute("message", "系统繁忙，请稍后重试");
			e.printStackTrace();
		}
		return "/backend/system/wx_user_car_info";
	}
}
