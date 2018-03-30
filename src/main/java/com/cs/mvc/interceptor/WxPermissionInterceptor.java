package com.cs.mvc.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cs.common.annotation.AuthValidate;
import com.cs.common.constant.Constant;
import com.cs.common.utils.CacheUtil;
import com.cs.system.entity.Station;
import com.cs.system.entity.WxUser;
import com.cs.wx.service.impl.WxServiceImpl;

/**
 * 微信访问拦截器
 * 
 * @author vincent
 * 
 */
public class WxPermissionInterceptor extends HandlerInterceptorAdapter {


	@Autowired
	WxServiceImpl wxServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		AuthValidate authValidate = null;
		if (handler instanceof HandlerMethod) {
			Method method = ((HandlerMethod) handler).getMethod();
			authValidate = method.getAnnotation(AuthValidate.class);
		}
		if (authValidate != null && authValidate.exceptAuth()) {
			return true;
		}
		
		// 获取请求方式
		String reqType = request.getHeader("X-Requested-With");
		// 获取登录用户及进入选中的检测站
 		WxUser wxUser = (WxUser) CacheUtil.getCacheObject(Constant.WX_USER_CACHE, request);
 		
 		//Station station = (Station) CacheUtil.getCacheObject(Constant.WX_STATION_CACHE, request);

 		if (wxUser == null) {
			if ("XMLHttpRequest".equals(reqType)) {
				// AJAX被拦截，返回字符串提示
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("用户未登录或登录超时,请返回公众号重新进入!");
				return false;
			} else {
				response.sendRedirect("/error/403.jsp");
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
