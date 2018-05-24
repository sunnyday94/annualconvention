/**
 * This class was created by sunny. It's distributed as
 * part of the annualconvention-model Mod.
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
 * File Created @ [2017年12月13日, 下午1:25:12 (CST)]
 */
package com.chunmi.annualconvention.vo;

import java.io.Serializable;

import com.chunmi.annualconvention.po.Users;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
public class UsersVo extends Users implements Serializable{

	/**
	 * 奖项id
	 */
	@Getter
	@Setter
	private Long prizeLevelId;
	
	/**
	 * 奖项名称
	 */
	@Getter
	@Setter
	private String prizeLevelName;
	
	/**
	 * 奖品名称
	 */
	@Getter
	@Setter
	private String prizeName;
	
	/**
	 * 中奖人数设置
	 */
	@Getter
	@Setter
	private Integer getPrizeNum;
	
	/**
	 * 中奖人id
	 */
	@Getter
	@Setter
	private Long[] getPrizeUserIds;
}
