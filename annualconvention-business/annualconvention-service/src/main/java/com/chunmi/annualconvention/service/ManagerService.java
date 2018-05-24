/**
 * This class was created by sunny. It's distributed as
 * part of the annualconvention-service Mod.
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
 * File Created @ [2017年12月11日, 下午4:38:00 (CST)]
 */
package com.chunmi.annualconvention.service;

import com.chunmi.annualconvention.po.Manager;

public interface ManagerService {

	/**
	 * 
	 * @description: <p class="detail">根据名称查询管理员信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午4:41:58
	 * @param @param userName
	 * @param @return
	 * @return Manager
	 */
	Manager selectVoteUsersByName(String userName);

}
