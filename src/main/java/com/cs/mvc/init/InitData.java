package com.cs.mvc.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.cs.common.constant.Constant;
import com.cs.common.entityenum.CommonStateEnum;
import com.cs.system.entity.GlobalConfig;
import com.cs.system.entity.Station;
import com.cs.system.entity.WxConfig;
import com.cs.system.service.GlobalConfigService;
import com.cs.system.service.StationService;
import com.cs.system.service.WxConfigService;

/**
 * 初始化数据Service，将常用数据缓存到内存中
 */
@Service
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
	
	//全局参数
	private static final Map<String,String> global = new HashMap<String,String>();
	//微信配置
	private static final Map<String,WxConfig> wxConfigMap = new HashMap<String,WxConfig>();
	//可用检测站列表
	private static final Map<String,Station> stationMap = new HashMap<String,Station>();
	//可用检测站列表
	private static List<Station> stationList = new ArrayList<Station>();
	
	@Autowired
	private GlobalConfigService globalConfigService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private WxConfigService wxConfigService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			try {
				//全局配置
				reloadGlobalConfig();
				//加载每个检测站并生成WxConfig
				reloadStation();
				
				reloadWxConfig();
				System.out.println("----------------------初始化数据成功----------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 重新加载全局配置
	 * @throws Exception
	 */
	public void reloadGlobalConfig() throws Exception{
		System.out.println("----------------------加载全局配置----------------------");
		List<GlobalConfig> list = globalConfigService.findAllData();
		if (list != null) {

			Map<String, String> newConfigMap = new HashMap<String, String>();
			for (GlobalConfig global : list) {
				newConfigMap.put(global.getDataKey(), global.getDataValue());
			}
			global.putAll(newConfigMap);
		}
	}
	
	/**
	 * 根据参数Key获取参数值
	 * @param dataKey
	 * @return
	 */
	public static String getGlobalValue(String dataKey){
		return global.get(dataKey);
	}
	
	
	/**
	 * 加载可用检测站
	 * @throws Exception
	 */
	public void reloadStation() throws Exception{
		System.out.println("----------------------加载检测站----------------------");
		stationList = stationService.findbyState(CommonStateEnum.USABLE);
		
		if (stationList != null) {
			Map<String, Station> newStationMap = new HashMap<String, Station>();
			for (int i = 0;i<stationList.size();i++) {
				Station station = stationList.get(i);
				newStationMap.put(station.getId(), station);
			}
			stationMap.putAll(newStationMap);
		}
	}
	
	public static List<Station> getStationList(){
		return stationList;
	}
	
	public static Station getStation(String id){
		return stationMap.get(id);
	}
	
	
	/**
	 * 加载微信配置
	 * @throws Exception
	 */
	public void reloadWxConfig() throws Exception{
		System.out.println("----------------------加载WxConfig----------------------");
		
		//获取当前可用的检测站ID
		List<String> stationIds = new ArrayList<String>();
		if (stationList != null) {
			for (int i = 0;i<stationList.size();i++) {
				Station station = stationList.get(i); 
				stationIds.add(station.getId());
			}
		}
		//获取检测站对应的公众号配置
		List<WxConfig> list = wxConfigService.findByStationId(stationIds);
		if (list != null) {
			Map<String, WxConfig> newWxConfigMap = new HashMap<String, WxConfig>();
			for (WxConfig wxConfig : list) {
				newWxConfigMap.put(wxConfig.getStationId(), wxConfig);
			}
			wxConfigMap.putAll(newWxConfigMap);
		}
		
		
		
		//没有配置的检测站，使用易车检的配置
		for (int i = 0; i < stationIds.size(); i++) {
			String stationId = stationIds.get(i);
			if(getWxConfig(stationId) == null){
				WxConfig wxConfig = new WxConfig();
				wxConfig.setStationId(stationId);
				wxConfig.setAppId(getGlobalValue("APPID"));
				wxConfig.setAppSecret(getGlobalValue("APPSECRET"));
				wxConfigMap.put(stationId, wxConfig);
			}
		}
		
		WxConfig wxConfig = new WxConfig();
		wxConfig.setStationId(Constant.WX_YCJ);
		wxConfig.setAppId(getGlobalValue("APPID"));
		wxConfig.setAppSecret(getGlobalValue("APPSECRET"));
		wxConfigMap.put(Constant.WX_YCJ, wxConfig);
		
	}
	
	public static WxConfig getWxConfig(String stationId){
		return wxConfigMap.get(stationId);
	}
}
