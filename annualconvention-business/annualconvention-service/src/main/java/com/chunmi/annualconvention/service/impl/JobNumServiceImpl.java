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
 * File Created @ [2017年12月27日, 上午11:08:22 (CST)]
 */
package com.chunmi.annualconvention.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chunmi.annualconvention.dao.JobNumsMapper;
import com.chunmi.annualconvention.service.JobNumService;

@Service("jobNumService")
@Transactional
public class JobNumServiceImpl implements JobNumService {
	@Autowired
	private JobNumsMapper jobNumsMapper;

	@Override
	public Integer existsJobNum(String jobNum) {
		return jobNumsMapper.existsJobNum(jobNum);
	}

}
