package com.chunmi.annualconvention.controller.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.chunmi.annualconvention.controller.annotations.Api;
import com.chunmi.annualconvention.po.Manager;
import com.chunmi.annualconvention.utils.Constant;

public class ManageInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) arg2;
		Method method = handlerMethod.getMethod();
		Api api = method.getAnnotation(Api.class);
		if (api == null) {
			Manager manager = (Manager) arg0.getSession().getAttribute(Constant.LOGIN_MANAGER);
			if (manager == null) {
				arg1.sendRedirect(arg0.getScheme() + "://" + arg0.getServerName() + ":" + arg0.getServerPort()
						+ arg0.getContextPath() + "/login");
				return false;
			} else {
				return true;
			}

		} else {
			return true;
		}

	}

}
