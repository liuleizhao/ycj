package com.cs.system.service;

import java.util.List;

import com.cs.mvc.service.BaseService;
import com.cs.system.entity.WxConfig;

/**
 * 微信公众号配置
 * @author llz
 *
 */
public interface WxConfigService extends BaseService<WxConfig, String> {
	
	public List<WxConfig> findByStationId(List<String> stationIdList) throws Exception;
}
