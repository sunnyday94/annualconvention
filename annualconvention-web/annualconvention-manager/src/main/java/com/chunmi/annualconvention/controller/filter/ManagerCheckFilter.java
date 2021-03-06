/**
 * This class was created by sunny. It's distributed as
 * part of the annualconvention-manager Mod.
 *
 * 版权所有(C) 上海纯米电子科技有限公司 2014-2023
 * Copyright 2014-2023 CHUNMI TECHNOLOGY CO..
 *
 * This software is the confidential and proprietary information of
 * CHUNMI Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with CHUNMI.
 *
 * File Created @ [2017年12月11日, 下午4:17:07 (CST)]
 */
package com.chunmi.annualconvention.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chunmi.annualconvention.po.Manager;
import com.chunmi.annualconvention.utils.Constant;

public class ManagerCheckFilter implements Filter{

	@Override
	public void destroy() {
		
		
	}

	/**
	 * 登录过滤
	 * TODO .
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String servletPath = req.getServletPath();	
		if(!servletPath.endsWith(".css") && !servletPath.endsWith(".js") && !servletPath.endsWith(".jpg") &&
				!servletPath.endsWith("checkLogin") && !servletPath.endsWith("login")&& !servletPath.endsWith("queryUsers") 
				&& !servletPath.endsWith("websocket") && !servletPath.endsWith("uploadPrizePic") && !servletPath.endsWith("register")
				&& !servletPath.endsWith("check-token") && !servletPath.endsWith("checkRegister") && !servletPath.endsWith("appTopPic")
				&& !servletPath.endsWith("schedulePic")) {
			Manager manager = (Manager) req.getSession().getAttribute(Constant.LOGIN_MANAGER);
			if(manager == null) {
				res.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/login");
				return;
			}
		}
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
