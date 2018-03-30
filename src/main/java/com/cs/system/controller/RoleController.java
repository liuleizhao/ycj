package com.cs.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cs.common.annotation.UserActionLog;
import com.cs.common.entityenum.LogTypeEnum;
import com.cs.mvc.dao.SqlCondition;
import com.cs.mvc.web.BaseController;
import com.cs.system.entity.Role;
import com.cs.system.entity.User;
import com.cs.system.service.RoleService;
import com.cs.system.service.UserService;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName:    RoleController.java
 * @Description:  角色控制器
 * @Author        xuj
 * @date          2016-10-25  下午5:02:25
 */
@Controller
@RequestMapping(value = "/backend/system/role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	private PageInfo<Role> pageInfo;
	private PageInfo<User> pageView;
	
	/**
	 * @throws Exception 
	 * @Description:  查询角色列表
	 * 
	 */
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Role role,Model model) throws Exception{
		SqlCondition sqlCondition = new SqlCondition();
		if(role != null){
			sqlCondition.addLikeCriterion("name LIKE ", role.getName());
		}
		pageInfo = roleService.findByCondition(sqlCondition, getCurrentPage(request), getCurrentPageSize(request));
		model.addAttribute("pageView", pageInfo);
		if(role!=null && StringUtils.isNotBlank(role.getName())){
			model.addAttribute("roleName", role.getName());
		}
		return "/backend/system/role_list";
	}

	/**
	 * @Description:  增加页面
	 * @param request
	 * @param role
	 * @param model
	 * @return        String
	 * @date          2015-11-10  下午5:57:14
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUI(HttpServletRequest request,Model model){
		return "/backend/system/role_info";
	}
	
	/**
	 * @Description:  增加保存
	 * @param request
	 * @param role
	 * @param ids
	 * @param model
	 * @return        String
	 * @throws Exception 
	 * @date          2015-11-10  下午5:57:33
	 */
	@UserActionLog(userLogType = LogTypeEnum.AUTHORITY,description="增加角色")
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request,Role role,String ids,Model model) throws Exception{
		//角色名称验证
		Role  oldRole = roleService.findByName(role.getName().trim());
		if(oldRole!=null){
			model.addAttribute("errorMessage","角色名称【"+role.getName()+"】已存在，请重新输入");
			return "/backend/system/role_add";
		}
		role.setCreator(getBackendUser(request).getName());
		String[] resourceIds = null;
		if(StringUtils.isNotEmpty(ids)){
			resourceIds = ids.split("\\,");
		}
        roleService.saveRoleAndRelation(resourceIds, role);
        model.addAttribute("message","新增角色【"+role.getName()+"】成功");
        return list(request,null,model); 
	}
	
	/**
	 * @Description:  修改页面
	 * @param request
	 * @param roleId
	 * @param model
	 * @return        String
	 * @date          2015-11-10  下午5:57:42
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String editUI(HttpServletRequest request,String roleId,Model model) throws Exception{
		 Role role  = roleService.selectByPrimaryKey(roleId);
		 if(null == role)
		 {
			 model.addAttribute("message","未找到对应的角色信息，无法修改！");
			 return list(request, null, model);
		 }else
		 {
			 model.addAttribute("role",role);
			 return "/backend/system/role_info";
		 }
	}
	
	/**
	 * @Description:  修改保存
	 * @param request
	 * @param role
	 * @param ids
	 * @param model
	 * @return        String
	 * @throws Exception 
	 * @date          2015-11-10  下午5:57:52
	 */
	@UserActionLog(userLogType = LogTypeEnum.AUTHORITY,description="修改角色")
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request, Role role , String ids,Model model) throws Exception{
		String[] resourceIds = null;
		if(StringUtils.isNotEmpty(ids)){
			resourceIds = ids.split("\\,");
		}
		// 添加角色 权限不是必填，所以再次不用判断是否选择了权限
		roleService.updateRoleAndRelation(resourceIds, role);
		model.addAttribute("message","修改角色成功");
		return list(request,null,model); 
	}
	
	
	@RequestMapping(value="/views",method=RequestMethod.GET)
	public String view(HttpServletRequest request,String roleId,Model model) throws Exception{
		 Role role  = roleService.selectByPrimaryKey(roleId);
		 if(null == role)
		 {
			 model.addAttribute("message","未找到对应的角色信息，无法查看！");
			 return list(request, null, model);
		 }else
		 {
			 pageView = userService.findByRole(roleId, getCurrentPage(request),4);
			 model.addAttribute("role",role);
			 model.addAttribute("pageView",pageView);
			 return "/backend/system/role_view";
		 }
	}
	@UserActionLog(userLogType = LogTypeEnum.AUTHORITY,description="删除角色")
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(HttpServletRequest request, String roleId ,Model model) throws Exception{
		if(StringUtils.isNotBlank(roleId)){
			roleService.deleteById(roleId);
			model.addAttribute("message","删除角色成功");
		}else{
			model.addAttribute("message", "删除角色失败，传入参数为空！");
		}
		return list(request,null,model);
	}
	
	@RequestMapping(value = "/check")
	@ResponseBody
	public boolean checkName(String name) throws Exception {
		boolean state = false;
		if (!StringUtils.isBlank(name)) {
			Role role = roleService.findByName(java.net.URLDecoder.decode(
					java.net.URLDecoder.decode(name.trim(), "UTF-8"), "UTF-8"));
			if (role == null) {
				state = true;
			}
		}
		return state;
	}
}
