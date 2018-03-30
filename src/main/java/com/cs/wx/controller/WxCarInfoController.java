package com.cs.wx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.common.entityenum.CarTypeEnum;
import com.cs.common.entityenum.DriverTypeEnum;
import com.cs.common.entityenum.FuelTypeEnum;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.entityenum.UseCharaterEnum;
import com.cs.common.entityenum.VehicleCharacterEnum;
import com.cs.common.entityenum.VehicleTypeEnum;
import com.cs.common.utils.json.JSONObject;
import com.cs.common.utils.json.XML;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.BookInfo;
import com.cs.system.entity.CarInfo;
import com.cs.system.entity.WxUser;
import com.cs.system.service.CarInfoService;

/**
 * 手机端登录控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/wx/carInfo")
public class WxCarInfoController extends BaseController{
	
	
	@Autowired
	private CarInfoService carInfoService;
	
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,Model model) throws Exception{
		
		WxUser wxuser = getAppUser(request);
		List<CarInfo> carInfoList= carInfoService.findbyUserId(wxuser.getId());
		model.addAttribute("carInfoList", carInfoList);
		
		return "wx/car_info_list";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model) throws Exception{
		
		commonAttribute(model);
		
		return "wx/car_info_detail";
	}
	
	@ResponseBody
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public Map<String,String> add(HttpServletRequest request, CarInfo carInfo) throws Exception{
		WxUser wxuser = getAppUser(request);
		carInfo.setUserId(wxuser.getId());
		carInfo.setCreateDate(new Date());
		Integer count = carInfoService.insert(carInfo);
		if(count>0){
			return InterfaceUtils.buildAjaxMap("",InterfaceCodeEnum.SUCCEED, "车辆信息添加成功");
		}else{
			return InterfaceUtils.buildAjaxMap("",InterfaceCodeEnum.FAILED, "车辆信息添加失败");
		}
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String editUI(HttpServletRequest request,Model model,String carInfoId) throws Exception{
		if(StringUtils.isNotBlank(carInfoId)){
			CarInfo carInfo = carInfoService.selectByPrimaryKey(carInfoId);
			if(carInfo != null){
				model.addAttribute("carInfo", carInfo);
				commonAttribute(model);
				return "wx/car_info_detail";
			}
		}
		model.addAttribute("message", "获取车辆信息失败");
		return forward("/wx/carInfo/list");
	}
	
	@ResponseBody
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public Map<String,String> edit(HttpServletRequest request,CarInfo carInfo) throws Exception{
		Integer count = carInfoService.updateByPrimaryKeySelective(carInfo);
		if(count>0){
			return InterfaceUtils.buildAjaxMap("",InterfaceCodeEnum.SUCCEED, "车辆信息修改成功");
		}else{
			return InterfaceUtils.buildAjaxMap("",InterfaceCodeEnum.FAILED, "车辆信息修改失败");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/del",method = RequestMethod.POST)
	public Map<String,String> del(HttpServletRequest request,String carInfoId) throws Exception{
		if(StringUtils.isNotBlank(carInfoId)){
			Integer count = carInfoService.deleteByPrimaryKey(carInfoId);
			if(count>0){
				return InterfaceUtils.buildAjaxMap("",InterfaceCodeEnum.SUCCEED, "车辆信息删除成功");
			}
		}
		return InterfaceUtils.buildAjaxMap("",InterfaceCodeEnum.FAILED, "车辆信息删除失败");
	}
	
	
	/**
	 * 获取备案公司
	 * @param request
	 * @param bookInfo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getCompany",method = RequestMethod.POST)
	public String getBookTime(HttpServletRequest request,BookInfo bookInfo) throws Exception{
		String timeXml = InterfaceUtils.callInterface(InterfaceEnum.JK09,null);
		timeXml = timeXml.replace("companyName", "text");
		JSONObject timeJSONObj = XML.toJSONObject(timeXml);
		
		return timeJSONObj.getJSONObject("ResponseMessage").toString();
		
	}
	
	private void commonAttribute(Model model){
		
		
		List<VehicleTypeEnum> vehicleTypeList = VehicleTypeEnum.getAll();
		List<CarTypeEnum> carTypeList = CarTypeEnum.getAll();
		List<VehicleCharacterEnum> vehicleCharacterList = VehicleCharacterEnum.getAll();
		List<DriverTypeEnum> driverTypeList = DriverTypeEnum.getAll();
		List<FuelTypeEnum> fuelTypeList = FuelTypeEnum.getAll();
		List<UseCharaterEnum> useCharaterList = UseCharaterEnum.getAll();
		
		model.addAttribute("vehicleTypeList", vehicleTypeList);
		model.addAttribute("carTypeList", carTypeList);
		model.addAttribute("vehicleCharacterList", vehicleCharacterList);
		model.addAttribute("driverTypeList", driverTypeList);
		model.addAttribute("fuelTypeList", fuelTypeList);
		model.addAttribute("useCharaterList", useCharaterList);
	}

}
