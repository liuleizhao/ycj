package com.cs.mvc.init;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class WebContextListener extends ContextLoaderListener{
	
	@Override
	public WebApplicationContext initWebApplicationContext(
			ServletContext servletContext) {
		return super.initWebApplicationContext(servletContext);
	}
			
}
