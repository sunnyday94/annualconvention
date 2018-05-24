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
 * File Created @ [2017年12月27日, 上午11:08:02 (CST)]
 */
package com.chunmi.annualconvention.service;

public interface JobNumService {

	/**
	 * 
	 * @description: <p class="detail">判断工号是否存在</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月27日-上午11:10:34
	 * @param @param jobNum
	 * @param @return
	 * @return Integer
	 */
	Integer existsJobNum(String jobNum);

}
