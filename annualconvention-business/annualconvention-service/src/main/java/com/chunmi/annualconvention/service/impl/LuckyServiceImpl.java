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
 * File Created @ [2017年12月21日, 下午5:01:17 (CST)]
 */
package com.chunmi.annualconvention.service.impl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chunmi.annualconvention.dao.PrizeMapper;
import com.chunmi.annualconvention.dao.PrizeRecordMapper;
import com.chunmi.annualconvention.dao.UsersMapper;
import com.chunmi.annualconvention.po.PrizeRecord;
import com.chunmi.annualconvention.service.LuckyService;
import com.chunmi.annualconvention.vo.UsersVo;

@Service
@Transactional
public class LuckyServiceImpl implements LuckyService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	@Autowired
	private PrizeRecordMapper prizeRecordMapper;

	@Override
	public void savePrizeRecord(UsersVo usersVo) {
		Long prizeLevelId = usersVo.getPrizeLevelId(); //奖项id
		Long prizeId = prizeMapper.selectPrizeIdByPrizeLevelId(prizeLevelId); //得到奖品id
		List<PrizeRecord> prizeRecordList = new ArrayList<PrizeRecord>();
		//数组转Set
		Set<Long> getPrizeUserIds = new HashSet<Long>();
		Long[] getPrizeUserIdsArray = usersVo.getGetPrizeUserIds();
		for(int i = 0;i<getPrizeUserIdsArray.length;i++) {
			getPrizeUserIds.add(getPrizeUserIdsArray[i]);
		}
		usersMapper.updateUserPrizeStatus(getPrizeUserIds); //更新中奖状态
		
		Iterator<Long> it = getPrizeUserIds.iterator();
		while(it.hasNext()) {
			PrizeRecord  prizeRecord = new PrizeRecord();
			prizeRecord.setPrizeId(prizeId);
			prizeRecord.setUserId(it.next());
			prizeRecordList.add(prizeRecord);
		}
		prizeRecordMapper.insertPrizeRecordList(prizeRecordList);
	}

}
