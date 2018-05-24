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
 * File Created @ [2017年12月11日, 下午6:24:44 (CST)]
 */
package com.chunmi.annualconvention.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chunmi.annualconvention.dao.PrizeLevelMapper;
import com.chunmi.annualconvention.dao.PrizeMapper;
import com.chunmi.annualconvention.dao.PrizeRecordMapper;
import com.chunmi.annualconvention.po.Prize;
import com.chunmi.annualconvention.po.PrizeLevel;
import com.chunmi.annualconvention.service.PrizeService;
import com.chunmi.annualconvention.vo.UsersVo;

@Service("prizeService")
@Transactional
public class PrizeServiceImpl implements PrizeService {
	
	@Autowired
	private PrizeLevelMapper prizeLevelMapper;
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	@Autowired
	private PrizeRecordMapper prizeRecordMapper;

	@Override
	public List<PrizeLevel> selectAllPrizeLevel() {
		return prizeLevelMapper.selectAllPrizeLevel();
	}

	@Override
	public Integer savePrize(Prize prize) {
		return prizeMapper.insertSelective(prize);
	}

	@Override
	public Integer delPrizeById(Long id) {
		return prizeMapper.delPrizeById(id);
	}

	@Override
	public List<Prize> selectAllPrize() {
		return prizeMapper.selectAllPrize();
	}

	@Override
	public Integer addPrizeLevel(PrizeLevel prizeLevel) {
		return prizeLevelMapper.insertSelective(prizeLevel);
	}

	@Override
	public Integer selectPrizeRecordByPrizeLevelId(UsersVo usersVo) {
		return prizeRecordMapper.selectPrizeRecordByCondition(usersVo);
	}

	@Override
	public Long selectPrizeIdByPrizeLevelId(Long prizeLevelId) {
		return prizeMapper.selectPrizeIdByPrizeLevelId(prizeLevelId);
	}

	@Override
	public List<PrizeLevel> selectPrizeLevelWithPrize() {
		return prizeLevelMapper.selectPrizeLevelWithPrize();
	}

	@Override
	public String getPrizePicByPrizeLevelId(Long prizeLevelId) {
		return prizeMapper.getPrizePicByPrizeLevelId(prizeLevelId);
	}

}
