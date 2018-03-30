package com.cs.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cs.mvc.web.BaseController;
import com.cs.system.vo.TemplateSender;
import com.cs.wx.service.impl.WxServiceImpl;
import com.soecode.wxtools.bean.result.TemplateListResult;
import com.soecode.wxtools.bean.result.TemplateResult;
import com.soecode.wxtools.bean.result.TemplateSenderResult;
import com.soecode.wxtools.bean.result.WxError;


/**
 * @author lxy
 *
 */
@Controller
@RequestMapping(value = "/backend/system/template")
public class TemplateController extends BaseController{
	
	@Autowired
	WxServiceImpl WxServiceImpl;
	
	String stationId;
	TemplateSender sender;
	String template_id_short;
	
	/**
	 *发送模板消息
	 */
	
	@RequestMapping(value="/templateSend")
	public String templateSend(HttpServletRequest request,Model model) throws Exception{
		stationId=getBackendUser(request).getStationId();
		TemplateSenderResult templateSenderResult= WxServiceImpl.templateSend(stationId,sender);
		model.addAttribute("templateSenderResult", templateSenderResult);
		return "";
	}
	
	/**
	 *获取对应模板编号的模板id
	 */
	@RequestMapping(value="/templateGetId")
	public String templateGetId(HttpServletRequest request,Model model) throws Exception{
		stationId=getBackendUser(request).getStationId();
		TemplateResult templateResult= WxServiceImpl.templateGetId(stationId,template_id_short);
		model.addAttribute("TemplateResult", templateResult);
		return "";
	}
	
	/**
	 *获取模板列表
	 */
	@RequestMapping(value="/templateGetList")
	public String templateGetList(HttpServletRequest request,Model model) throws Exception{
		stationId=getBackendUser(request).getStationId();
		TemplateListResult templateListResult= WxServiceImpl.templateGetList(stationId);
		model.addAttribute("TemplateListResult", templateListResult);
		return "backend/template/template_list";
	}
	
	/**
	 *删除模板
	 */
	@RequestMapping(value="/templateDelete")
	public String templateDelete(HttpServletRequest request,Model model,String template_id) throws Exception{
		stationId=getBackendUser(request).getStationId();
		WxError result= WxServiceImpl.templateDelete(stationId,template_id);
		if(result.getErrcode()==0){
		    model.addAttribute("message", "成功删除模板");
		}else{
			model.addAttribute("message", "删除模板失败");
		}
		return templateGetList(request,model);
	}
	
}
