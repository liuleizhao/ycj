package com.cs.common.utils.webservice;

import java.util.HashMap;
import java.util.Map;

import com.cs.common.entityenum.InterfaceCodeEnum;
import com.cs.common.entityenum.InterfaceEnum;
import com.cs.common.utils.CommonAESUtil;
import com.cs.common.utils.json.JSONObject;
import com.cs.mvc.init.InitData;
import com.cs.system.entity.BookInfo;



/**
 * WebService请求封装类
 * @author
 * 
 */
public class InterfaceUtils {
	public static String callInterface(InterfaceEnum interfaceEnum,Object object) throws Exception{
		String interfaceName = interfaceEnum.name();
		String fatherNode = interfaceEnum.getFatherNode();
		
		Map<String,String> map = new HashMap<String, String>();
		
		BookInfo bookInfo = null;
		
		if(InterfaceEnum.JK08.name().equals(interfaceName)){
			map.put("bookNumber",(String)object);
		}else if(InterfaceEnum.JK09.name().equals(interfaceName)){
				//无操作
		}else{
			bookInfo = (BookInfo)object;
		}
		
		if(InterfaceEnum.JK03.name().equals(interfaceName)){
			map.put("mobile", "18888888888");
			map.put("stationId", bookInfo.getStationId());
			map.put("vehicleType", bookInfo.getVehicleType().getId());
			map.put("vehicleingCharacter", bookInfo.getVehicleCharacter().getId().toString());
			map.put("carTypeId", bookInfo.getCarTypeId().getId());
			map.put("driverType", bookInfo.getDriverType().getId().toString());
		}else
		
		if(InterfaceEnum.JK04.name().equals(interfaceName)){
			map.put("mobile", "18888888888");
			map.put("stationId", bookInfo.getStationId());
			map.put("vehicleType", bookInfo.getVehicleType().getId());
			map.put("vehicleingCharacter", bookInfo.getVehicleCharacter().getId().toString());
			map.put("carTypeId", bookInfo.getCarTypeId().getId());
			map.put("driverType", bookInfo.getDriverType().getId().toString());
			map.put("bookDate", bookInfo.getBookDate());
		}else
		
		if(InterfaceEnum.JK05.name().equals(interfaceName)){
			map.put("mobile", bookInfo.getMobile());
			map.put("stationId", bookInfo.getStationId());
			map.put("vehicleType", bookInfo.getVehicleType().getId());
			map.put("vehicleingCharacter", bookInfo.getVehicleCharacter().getId().toString());
			map.put("carTypeId", bookInfo.getCarTypeId().getId());
			map.put("driverType", bookInfo.getDriverType().getId().toString());
			map.put("frameNumber", bookInfo.getFrameNumber());
			
			if(bookInfo.getUseCharater() != null){
				map.put("useCharater", bookInfo.getUseCharater().getId());
				map.put("busCompanyId", bookInfo.getBusCompanyId());
			}
			
			map.put("bookerName", bookInfo.getBookerName());
			map.put("idNumber", bookInfo.getIdNumber());
			
			map.put("platNumber", bookInfo.getPlatNumber());
			map.put("newflag", bookInfo.getNewflag());
			
			map.put("idTypeId", bookInfo.getIdTypeId().getId());
			map.put("fuelType", bookInfo.getFuelType().getId().toString());
			map.put("bookDate", bookInfo.getBookDate());
			map.put("bookTime", bookInfo.getBookTime());
			map.put("bookingChannel", bookInfo.getBookChannel().getId().toString());
		}else
		if(InterfaceEnum.JK06.name().equals(interfaceName)){
			map.put("mobile", bookInfo.getMobile());
			map.put("bookerName", bookInfo.getBookerName());
			map.put("idNumber", bookInfo.getIdNumber());
			map.put("platNumber", bookInfo.getPlatNumber());
		}else
		
		if(InterfaceEnum.JK07.name().equals(interfaceName)){
			map.put("mobile", bookInfo.getMobile());
			map.put("bookNumber", bookInfo.getBookerName());
			map.put("verifyCode", bookInfo.getVerifyCode());
		}

		String src = null;
		try {
			src = CommonAESUtil.encryptAES(buildRequestXml(fatherNode,map));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String url = InitData.getGlobalValue("INTERFACE_URL");
		String account = InitData.getGlobalValue("INTERFACE_ACCOUNT");
		String password = InitData.getGlobalValue("INTERFACE_PASSWORD");
		
		String date= null;
		try {
			date = WebServiceUtils.sendRequest(url,interfaceName,account,password,src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(date);
		
		return date.replace("msg", "message");
	}
	
	public static  String buildRequestXml(String fatherNode,Map<String,String> param){
		StringBuffer xmlDoc = new StringBuffer();
		xmlDoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>");
		if(fatherNode!=null){
			xmlDoc.append("<"+fatherNode+">");
		}
		for (Map.Entry<String, String> entry : param.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			xmlDoc.append("<"+key+">"+value+"</"+key+">");
		  }
		if(fatherNode!=null){
			xmlDoc.append("</"+fatherNode+">");
		}
		xmlDoc.append("</root>");
		System.out.println(xmlDoc.toString());
		return xmlDoc.toString();
	}
	
	public static  Map<String,String> buildAjaxMap(String result,InterfaceCodeEnum code, String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("result", result);
		map.put("state", code.getId());
		map.put("message", message);
		return map;
	}
	
	public static  Map<String,Object> buildAjaxMap(Object result,InterfaceCodeEnum code,String message,Integer currentPage){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("state", code.getId());
		map.put("message", message);
		map.put("currentPage", currentPage);
		return map;
	}
	
	public static  String buildAjaxJson(String result,InterfaceCodeEnum code, String message){
		JSONObject json = new JSONObject();
		json.append("result", result);
		json.append("state", code.getId());
		json.append("message", message);
		return json.toString();
	}
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("mobile", "17665202833");
		map.put("bookNumber", "18031619742C");
		map.put("verifyCode", "239696");
		String src = null;
		try {
			src = CommonAESUtil.encryptAES(buildRequestXml(null,map));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取webservice 请求配置
		String account = "admintest";
		String password = "3691308f2a4c2f6983f2880d32e29c86";
		String url = "http://app.stc.gov.cn:8092/mot/services/bookService?wsdl";
		
		try {
			WebServiceUtils.sendRequest(url,"JK08",account,password,src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
