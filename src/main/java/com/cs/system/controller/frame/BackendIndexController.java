package com.cs.system.controller.frame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cs.common.annotation.AuthValidate;
import com.cs.common.entityenum.ResourceTypeEnum;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.Resource;
import com.cs.system.entity.User;
import com.cs.system.service.ResourceService;




@Controller
@RequestMapping(value = "/backend")
public class BackendIndexController extends BaseController {

	/** 资源相关服务接口 */
	@Autowired
	private ResourceService resourceService;

	/**
	 * @描述：跳转到iframeInxdex
	 */
	@AuthValidate(exceptResource = true)
	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String iframeIndex(HttpServletRequest request,Model model) throws Exception {
		User user = this.getBackendUser(request);
		model.addAttribute("user",this.getBackendUser(request));
		getResource(user, model);
		// 获取当前登陆者的所有权限
		return "backend/index";
	}
	
	/**
	 * @描述：引入home页面
	 */
	@AuthValidate(exceptResource = true)
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model)
			throws Exception {
		return "backend/home";
	}
	
	public void getResource(User user,Model model) throws Exception
	{
		//获取权限资源
		if (user != null) {
			String userId = user.getId();
			if(user.getIsAdmin().equals(1)){
				userId = null;
			}
			// 获取顶级模块
			List<Resource> topResources = resourceService.findResourceBy(userId,"",ResourceTypeEnum.MODULE);
			if (topResources != null && topResources.size() == 1) {
				Resource topResource = topResources.get(0);
				// 获取菜单
				List<Resource> parentResources  = resourceService.findResourceBy(userId,topResource.getId(), ResourceTypeEnum.MENU);
				if (parentResources != null && parentResources.size() > 0) {
					 Map<String, List<Resource>> childResources = new HashMap<String , List<Resource>>();	
					for (Resource resource : parentResources) {
						List<Resource> a = resourceService.findResourceBy(userId,resource.getId(), ResourceTypeEnum.MENU);
						if (a != null && a.size() > 0) {
							// 获取二级目录下的资源信息
							childResources.put(resource.getId(), a);
						}
					}
					model.addAttribute("childResources", childResources);
					
				}
				model.addAttribute("parentResources", parentResources);
			}
		}
	}
}
