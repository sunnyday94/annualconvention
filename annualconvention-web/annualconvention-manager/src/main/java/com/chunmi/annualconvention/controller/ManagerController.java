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
 * File Created @ [2017年12月11日, 下午2:12:01 (CST)]
 */
package com.chunmi.annualconvention.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.chunmi.annualconvention.po.Manager;
import com.chunmi.annualconvention.service.ManagerService;
import com.chunmi.annualconvention.utils.Constant;
import com.chunmi.annualconvention.utils.MD5Util;

@Controller
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	/**
	 * 
	 * @description: <p class="detail">跳转登录页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午4:14:38
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="login")
	public String goToLoginIndex() {
		return "login";
	}
	


	/**
	 * 
	 * @description: <p class="detail">登录验证</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午4:39:37
	 * @param @param request
	 * @param @param userName
	 * @param @param userPassword
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@PostMapping(value="checkLogin")
	public String checkLogin(HttpServletRequest request,
			@RequestParam("userName")String userName,
			@RequestParam("userPassword")String userPassword,
			Model model) {
		Manager manager = managerService.selectVoteUsersByName(userName);
		if(manager==null) {
			model.addAttribute("error", "User does not exist");  //用户不存在
		}else {
			if(manager.getUserPassword().equals(MD5Util.MD5Encryption(userPassword).toLowerCase())) {
				if(manager.getDelFlag().equals("1")) {
					model.addAttribute("error","User disabled");   //用户被禁用
				}else {
					request.getSession().setAttribute(Constant.LOGIN_MANAGER, manager);
					return "redirect:main";                                //通过登录验证，跳转首页页面                 
				}
			}else {
				model.addAttribute("error","User name and password do not match");     //密码错误
			}
		}
		return "login";
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">登录主页面</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午5:45:41
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/main")
	public String goToMain(HttpServletRequest request,Model model) {
		model.addAttribute(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		return "main";
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">管理员退出</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午6:07:16
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/signOut")
	public String signOut(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.LOGIN_MANAGER); 
		//返回登录页
		return "redirect:login";
	}
  
}
