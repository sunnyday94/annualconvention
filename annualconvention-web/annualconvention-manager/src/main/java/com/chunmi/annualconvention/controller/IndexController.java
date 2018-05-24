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
 * File Created @ [2017年12月11日, 下午4:10:34 (CST)]
 */
package com.chunmi.annualconvention.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	/**
	 * 
	 * @description: <p class="detail">登录页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午4:10:44
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/")
	public String index(Model model) {
		return "redirect:/login";
	}
}
