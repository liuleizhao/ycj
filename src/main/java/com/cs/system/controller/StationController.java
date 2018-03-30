package com.cs.system.controller;

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

import com.cs.common.entityenum.CommonStateEnum;
import com.cs.common.entityenum.DistrictEnum;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.UserTypeEnum;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.init.InitData;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.Station;
import com.cs.system.entity.User;
import com.cs.system.entity.WxConfig;
import com.cs.system.entity.WxMenu;
import com.cs.system.service.StationService;
import com.cs.system.service.WxConfigService;
import com.cs.system.service.WxMenuService;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName:    StationController.java
 * @Description:  检测站信息控制器
 * @Author        
 * @date          2016-11-03 下午5:02:25
 */
@Controller
@RequestMapping(value = "/backend/system/station")
public class StationController  extends BaseController{
	
	@Autowired
	private StationService stationService;
	@Autowired
	private WxConfigService wxConfigService;
	
	@Autowired
	private WxMenuService wxMenuService;

	
	@Autowired
	private InitData initData;
	
	private PageInfo<Station> pageView;
	
	/**
	 * @throws Exception
	 * @Description: 查询检测站列表
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model,Station station) throws Exception {
		SqlCondition sqlCondition = new SqlCondition();
		if(station != null){
			sqlCondition.addSingleCriterion("CODE = ", station.getCode());
			sqlCondition.addLikeCriterion("NAME LIKE ", station.getName());
		}
		sqlCondition.addAscOrderbyColumn("ORDER_NUM");
		pageView = stationService.findByCondition(sqlCondition,getCurrentPage(request),getCurrentPageSize(request));
		
		model.addAttribute("pageView", pageView);
		model.addAttribute("station", station);
		return "backend/system/station_list";
	}

	/**
	 * @Description: 跳转到添加页面
	 * @param model
	 * @return String
	 * @throws Exception
	 * @date 2016-10-31 下午12:12:33
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model) throws Exception {
		
		List<DistrictEnum> districtList = DistrictEnum.getAll();
		List<CommonStateEnum> stateList = CommonStateEnum.getAll();
		model.addAttribute("districtList", districtList);
		model.addAttribute("stateList", stateList);
		
		return "backend/system/station";
	}

	/**
	 * @Description: 执行添加的方法
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, Model model, Station station)throws Exception {
		try {
			String code = station.getCode();
			String name = station.getName();
			if(StringUtils.isBlank(code) ||StringUtils.isBlank(name))
			{
				model.addAttribute("message","编码和名称不能为空！");
				return addUI(request, model);
			} 
			
			if(!this.checkCodeExisted(code)){
				model.addAttribute("message","检测站编码已存在！");
				return addUI(request, model);
			}
			if(!this.checkNameExisted(name)){
				model.addAttribute("message","检测站名称已存在！");
				return addUI(request, model);
			}
			station.setCreateDate(new Date());
			int count = stationService.insert(station);
			if(count > 0){
				model.addAttribute("message","添加检测站信息【" + station.getName() + "】成功！");
				initData.reloadStation();
				initData.reloadWxConfig();
				return list(request, model,station);
			}else{
				model.addAttribute("message","添加检测站信息【" + station.getName() + "】失败！");
				return addUI(request,model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","添加检测站信息【" + station.getName() + "】失败！");
			return addUI(request,model);
		}
	}

	/**
	 * @Description: 跳转到修改页面
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUI(HttpServletRequest request, Model model,String id) throws Exception {
		
		Station station = stationService.selectByPrimaryKey(id);
		if(null == station ){
			model.addAttribute("message","修改用户信息失败，未找到对应的用户信息！");
			return list(request,model,null);
		}
		List<DistrictEnum> districtList = DistrictEnum.getAll();
		List<CommonStateEnum> stateList = CommonStateEnum.getAll();
		
		model.addAttribute("station", station);
		model.addAttribute("districtList", districtList);
		model.addAttribute("stateList", stateList);
		
		return "backend/system/station";
	}

	/**
	 * @Description: 执行修改的方法
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,Station station) throws Exception {
		try {
			// 获取当前要修改
			Station currentstationInfo = stationService.selectByPrimaryKey(station.getId());
			if(null == currentstationInfo)
			{
				model.addAttribute("message","修改检车站信息失败，未找到对应的检车站信息！");
				return list(request, model,null);
			}
			station.setName(null);
			station.setCode(null);
			int count = stationService.updateByPrimaryKeySelective(station);
			if(count > 0){
				model.addAttribute("message", "修改检测站成功！");
				initData.reloadStation();
				initData.reloadWxConfig();
				return list(request, model,station);
			}else{
				 model.addAttribute("message", "修改检测站失败！");
				 return editUI(request,model,station.getId());
			}
		}catch (Exception e) {
			 e.printStackTrace();
			 model.addAttribute("message", "修改检测站失败！");
			 return editUI(request,model,station.getId());
		}
	}
	
	/**
	 * 删除
	 * @param stationId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request,Model model,String id) throws Exception{
		if(StringUtils.isNotBlank(id)){
			int count = stationService.deleteByPrimaryKey(id);
			if(count > 0){
				initData.reloadStation();
				initData.reloadWxConfig();
				model.addAttribute("message", "删除检查站成功");
			}else{
				model.addAttribute("message", "删除检测站失败！");
			}
			model.addAttribute("message", "删除检查站成功");
		}else{
			model.addAttribute("message", "删除检查站失败，传入参数为空！");
		}
		return list(request,model,null);
	}
	
	@RequestMapping(value = "/checkCode")
	@ResponseBody
	public boolean checkCodeExisted(String code) throws Exception{
		boolean result = false;
		if(StringUtils.isNotBlank(code) && null == stationService.findByCode(code)){
			result = true;
		}
		return result;
	}
	
	@RequestMapping(value = "/checkName")
	@ResponseBody
	public boolean checkNameExisted(String name) throws Exception{
		boolean result = false;
		if(StringUtils.isNotBlank(name)){
			name = java.net.URLDecoder.decode(java.net.URLDecoder.decode(name, "UTF-8"), "UTF-8");
			if(null ==stationService.findByName(name)){
				result = true;
			}
		}
		return result;
	}
	
	
	
	
	@RequestMapping(value="/wxConfig", method = RequestMethod.GET)
	public String wxConfigUI(HttpServletRequest request,Model model,String id) throws Exception{
		
		User user = getBackendUser(request);
		
		if(UserTypeEnum.STATION.equals(user.getUserType())){
			id=user.getStationId();
		}
		
		if(StringUtils.isBlank(id)){
			throw new Exception("参数为空");
		}
		
		WxConfig wxConfig = InitData.getWxConfig(id);
		if(null == wxConfig){
			wxConfig = new WxConfig();
			wxConfig.setStationId(id);
		}
		
		model.addAttribute("wxConfig",wxConfig);
		return "backend/system/wx_config";
	}
	
	@ResponseBody
	@RequestMapping(value="/wxConfig", method = RequestMethod.POST)
	public Map<String,String> wxConfig(HttpServletRequest request,WxConfig wxConfig) throws Exception{
		
		if(StringUtils.isBlank(wxConfig.getStationId())||
				StringUtils.isBlank(wxConfig.getAppId())||
				StringUtils.isBlank(wxConfig.getAppSecret())){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "参数为空");
		}
		
		int count = 0;
		if(StringUtils.isBlank(wxConfig.getId())){
			count = wxConfigService.insert(wxConfig);
		}else{
			count = wxConfigService.updateByPrimaryKeySelective(wxConfig);
		}
		if(count>0){
			initData.reloadWxConfig();
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.SUCCEED, "保存成功");
		}else{
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "保存失败");
		}
	}
	
	
	@RequestMapping(value="/indexMenu", method = RequestMethod.GET)
	public String indexMenuUI(HttpServletRequest request,Model model,String id) throws Exception{
		
		User user = getBackendUser(request);
		
		if(UserTypeEnum.STATION.equals(user.getUserType())){
			id=user.getStationId();
		}
		
		if(StringUtils.isBlank(id)){
			throw new Exception("参数为空");
		}
		
		Station station = InitData.getStation(id);
		List<WxMenu> allMenuList = wxMenuService.findAllData();
		List<WxMenu> stationMenuList = stationService.findMenuById(id);
		
		model.addAttribute("station",station);
		model.addAttribute("allMenuList",allMenuList);
		model.addAttribute("stationMenuList",stationMenuList);
		
		return "backend/system/wx_index_menu";
	}
	
	@ResponseBody
	@RequestMapping(value="/indexMenu", method = RequestMethod.POST)
	public Map<String,String> indexMenu(HttpServletRequest request,String stationId,String[] menuIds) throws Exception{
		
		Station station = InitData.getStation(stationId);
		
		if(null == station){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "检测站不存在或未开启");
		}
		stationService.saveMenuByid(stationId,menuIds);
		return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.SUCCEED, "目录修改成功");
		
	}
	
	
	@RequestMapping(value="/details", method = RequestMethod.GET)
	public String detailsUI(HttpServletRequest request,Model model) throws Exception{
		
		User user = getBackendUser(request);
		String stationId = null;
		if(UserTypeEnum.STATION.equals(user.getUserType())){
			stationId=user.getStationId();
		}
		
		if(StringUtils.isBlank(stationId)){
			throw new Exception("参数为空");
		}
		
		List<DistrictEnum> districtList = DistrictEnum.getAll();
		
		model.addAttribute("districtList", districtList);
		
		Station station = InitData.getStation(stationId);
		model.addAttribute("station",station);
		return "backend/station/station";
	}
	
	@RequestMapping(value="/details", method = RequestMethod.POST)
	public String details(HttpServletRequest request,Model model,Station station) throws Exception{
		
		int count = stationService.updateByPrimaryKeySelective(station);
		if(count > 0){
			model.addAttribute("message", "修改检测站信息成功！");
			initData.reloadStation();
			initData.reloadWxConfig();
		}else{
			model.addAttribute("message", "修改检测站信息失败！");
		}
		List<DistrictEnum> districtList = DistrictEnum.getAll();
		model.addAttribute("districtList", districtList);
		model.addAttribute("station",InitData.getStation(station.getId()));
		return "backend/station/station";
	}
}
