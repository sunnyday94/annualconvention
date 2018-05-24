package com.chunmi.annualconvention.controller.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.chunmi.annualconvention.controller.filter.ManageInterceptor;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(new ManageInterceptor())
         .addPathPatterns("/**")
         .excludePathPatterns("/login")
         .excludePathPatterns("/checkLogin")
         .excludePathPatterns("/websocket");
		super.addInterceptors(registry);
	}
	
	

}
