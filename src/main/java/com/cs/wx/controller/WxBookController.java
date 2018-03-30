package com.cs.wx.controller;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.common.constant.Constant;
import com.cs.common.entityenum.BookChannelEunm;
import com.cs.common.entityenum.BookStateEnum;
import com.cs.common.entityenum.CarTypeEnum;
import com.cs.common.entityenum.DriverTypeEnum;
import com.cs.common.entityenum.FuelTypeEnum;
import com.cs.common.entityenum.IdTypeEnum;
import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.entityenum.OrderStateEnum;
import com.cs.common.entityenum.VehicleCharacterEnum;
import com.cs.common.entityenum.VehicleTypeEnum;
import com.cs.common.utils.CacheUtil;
import com.cs.common.utils.RandomValue;
import com.cs.common.utils.XmlHelper;
import com.cs.common.utils.json.JSONArray;
import com.cs.common.utils.json.JSONObject;
import com.cs.common.utils.json.XML;
import com.cs.common.utils.webservice.InterfaceUtils;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.BookInfo;
import com.cs.system.entity.CarInfo;
import com.cs.system.entity.Order;
import com.cs.system.entity.Station;
import com.cs.system.entity.WxUser;
import com.cs.system.service.BookInfoService;
import com.cs.system.service.CarInfoService;
import com.cs.system.service.OrderService;

/**
 * 手机端预约业务控制类
 * @author LLZ
 *
 */
@Controller
@RequestMapping(value = "/wx/book")
public class WxBookController extends BaseController {
	
	@Autowired
	private CarInfoService carInfoService;
	
	@Autowired
	private BookInfoService bookInfoService;
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value="/way",method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		return "wx/own_book_way";
	}
	
	@RequestMapping(value="/car",method = RequestMethod.GET)
	public String carInput(HttpServletRequest request,Model model) throws Exception{

		Station  station= (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE,request);
		List<VehicleTypeEnum> vehicleTypeList = VehicleTypeEnum.getAll();
		List<CarTypeEnum> carTypeList = CarTypeEnum.getAll();
		List<VehicleCharacterEnum> vehicleCharacterList = exclude(VehicleCharacterEnum.getAll(),station.getVehicleCharacters());
		List<DriverTypeEnum> driverTypeList = exclude(DriverTypeEnum.getAll(),station.getDriverTypes());
		List<FuelTypeEnum> fuelTypeList = FuelTypeEnum.getAll();
		
		model.addAttribute("station", station);
		model.addAttribute("vehicleTypeList", vehicleTypeList);
		model.addAttribute("carTypeList", carTypeList);
		model.addAttribute("vehicleCharacterList", vehicleCharacterList);
		model.addAttribute("driverTypeList", driverTypeList);
		model.addAttribute("fuelTypeList", fuelTypeList);
		
		return "wx/own_car_input";
	}
	
	
	@RequestMapping(value="/chooseCar",method = RequestMethod.GET)
	public String chooseCar(HttpServletRequest request,Model model) throws Exception{
		WxUser wxuser = getAppUser(request);
		List<CarInfo> carInfoList= carInfoService.findbyUserId(wxuser.getId());
		model.addAttribute("carInfoList", carInfoList);
		return "wx/own_car_input";
	}
	
	@RequestMapping(value="/input")
	public String input(HttpServletRequest request,Model model,CarInfo carInfo) throws Exception{
		
		if(carInfo == null){
			model.addAttribute("message", "请选择车辆");
			return forward("/wx/book/car");
		}
		
		CacheUtil.setCacheObject(Constant.WX_TEMP_DATA, carInfo, request);
		List<IdTypeEnum> idTypeList =  IdTypeEnum.getAll();
		model.addAttribute("idTypeList", idTypeList);
		return "wx/own_book_input";
	}
	
	@ResponseBody
	@RequestMapping(value="/getDate",method = RequestMethod.POST)
	public String getBookDate(HttpServletRequest request,Model model,BookInfo bookInfo) throws Exception{
		
		CarInfo carInfo = (CarInfo) CacheUtil.getCacheObject(Constant.WX_TEMP_DATA,request);
		BeanUtilsBean2.getInstance().copyProperties(bookInfo, carInfo);
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE,request);
		bookInfo.setStationId(station.getId());
		try {
			String dateXml = InterfaceUtils.callInterface(InterfaceEnum.JK03,bookInfo);
			JSONObject dataJSONObj = XML.toJSONObject(dateXml);
			return dataJSONObj.getJSONObject("ResponseMessage").toString();
		} catch (Exception e) {
			return InterfaceUtils.buildAjaxJson("", InterfaceCodeEnum.FAILED, "获取可预约时间失败").toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getTime",method = RequestMethod.POST)
	public String getBookTime(HttpServletRequest request,Model model,BookInfo bookInfo) throws Exception{
		
		CarInfo carInfo = (CarInfo) CacheUtil.getCacheObject(Constant.WX_TEMP_DATA,request);
		BeanUtilsBean2.getInstance().copyProperties(bookInfo, carInfo);
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE,request);
		bookInfo.setStationId(station.getId());
		
		String timeXml = InterfaceUtils.callInterface(InterfaceEnum.JK04,bookInfo);
		try {
			JSONObject timeJSONObj = XML.toJSONObject(timeXml);
			JSONArray timeJSONArray = timeJSONObj.getJSONObject("ResponseMessage").getJSONObject("result").getJSONArray("AppTimeHelper");
			for (int i = 0;i<timeJSONArray.length();i++){
				JSONObject j = timeJSONArray.getJSONObject(i);
						j.put("text", j.getString("apptime") + "   预约量:"+j.getInt("yetnumber") +"/"+ j.getInt("maxnumber"));
			}
			return timeJSONObj.getJSONObject("ResponseMessage").toString();
		} catch (Exception e) {
			String result = XmlHelper.getValue("result", timeXml);
			return InterfaceUtils.buildAjaxJson("", InterfaceCodeEnum.FAILED, result);
		}
		
		
		
	}
	@ResponseBody
	@RequestMapping(value="/submit",method = RequestMethod.POST)
	public Map<String,String> writeBookInfo(HttpServletRequest request,BookInfo bookInfo,Order order) throws Exception{
		
		CarInfo carInfo = (CarInfo) CacheUtil.getCacheObject(Constant.WX_TEMP_DATA,request);
		BeanUtilsBean2.getInstance().copyProperties(bookInfo, carInfo);
		
		CacheUtil.removeCacheObject(Constant.WX_TEMP_DATA,request);
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE,request);
		bookInfo.setStationId(station.getId());
		bookInfo.setStationName(station.getName());
		
		bookInfo.setBookChannel(BookChannelEunm.TP_WECHAT);
		
		String appointXml = InterfaceUtils.callInterface(InterfaceEnum.JK05,bookInfo);
		
		String result = XmlHelper.getValue("result", appointXml);
		String message = XmlHelper.getValue("message", appointXml);
		String code = XmlHelper.getValue("code", appointXml);
		if(!InterfaceCodeEnum.SUCCEED.getId().equals(code)){
			return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, result);
		}
		bookInfo.setBookNumber(result);
		bookInfo.setVerifyCode(RegexMatches(message));
		bookInfo.setBookState(BookStateEnum.YYZ);
		bookInfo.setCreateDate(new Date());
		
		int count = bookInfoService.insert(bookInfo);
		
		if(count > 0){
			BeanUtilsBean2.getInstance().copyProperties(order, bookInfo);
			order.setOrderNumber(createOrderNumber(new Date()));
			order.setCreateDate(new Date());
			order.setState(OrderStateEnum.DZF);
			order.setWxUserId(getAppUser(request).getId());
			int ocount = orderService.insertSelective(order);
			if(ocount > 0){
				return InterfaceUtils.buildAjaxMap(order.getOrderNumber(), InterfaceCodeEnum.SUCCEED, InterfaceCodeEnum.SUCCEED.getDescription());
			}else{
				return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "预约成功，生成预约信息失败");
			}
		}
		return InterfaceUtils.buildAjaxMap("", InterfaceCodeEnum.FAILED, "预约成功，生成订单失败");
	} 
	
	@RequestMapping(value="/ok",method = RequestMethod.GET)
	public String bookOk(HttpServletRequest request,Model model,String orderNumber) throws Exception{
		
		Station station = (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE,request);
		
		
		Order order = orderService.findByOrderNumber(orderNumber);
		BookInfo bookInfo = bookInfoService.findByBookNumber(order.getBookNumber());
		
		model.addAttribute("order", order);
		model.addAttribute("bookInfo", bookInfo);
		model.addAttribute("station", station);
		return "wx/book_ok";
	}
	
	private static String RegexMatches(String message){
		String pattern = "预约验证码为：\\d{6}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(message);
		if(m.find()){
			Matcher m1 = Pattern.compile("\\d{6}").matcher(m.group());
			if(m1.find()){
				return m1.group();
			}
		}
		return "";
	}
	
	private static String createOrderNumber(Date createDate){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
		StringBuilder out = new StringBuilder(dateFormat.format(createDate));
		out.append(RandomValue.getRandomValue("mot"));
		return out.toString();
	}
	
	
	@SuppressWarnings("rawtypes")
	private static List exclude(List list,String keys) throws Exception {
		
		List newlist = new ArrayList();
		System.out.println(keys);
		String[]  keyarr = keys.split(",");
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Class userCla = (Class) o.getClass();//reportVo1是一个实体类
			Field  fs = userCla.getDeclaredField("id");
			fs.setAccessible(true);
			boolean state = false;
			for (int j = 0; j < keyarr.length; j++) {
				System.out.println(fs.get(o)+"++++"+keyarr[j]);
				if(String.valueOf(fs.get(o)).equals(keyarr[j])){
					state = true;
					break;
				}
			}
			if(state){
				newlist.add(o);
			}
		}
		return newlist;
	}
	
	
	
	public static void main(String args[]) {
		List<FuelTypeEnum> fuelTypeList = FuelTypeEnum.getAll();
		try {
			System.out.println(exclude(fuelTypeList,"1"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*String str = "预约成功：您的预约号为：171222108943,预约验证码为：452001,请妥善保管。您的预约名字为：庞xx";
		String pattern = "预约验证码为：\\d{6}";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());*/
	}
}
