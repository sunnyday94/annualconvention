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
 * File Created @ [2017年12月11日, 下午6:24:20 (CST)]
 */
package com.chunmi.annualconvention.service;

import java.util.List;

import com.chunmi.annualconvention.po.Prize;
import com.chunmi.annualconvention.po.PrizeLevel;
import com.chunmi.annualconvention.vo.UsersVo;

public interface PrizeService {

	/**
	 * 
	 * @description: <p class="detail">查询所有奖品等级</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月11日-下午6:27:42
	 * @param @return
	 * @return List<PrizeLevel>
	 */
	List<PrizeLevel> selectAllPrizeLevel();

	/**
	 * 
	 * @description: <p class="detail">添加奖品</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-上午9:38:09
	 * @param @param prize
	 * @param @return
	 * @return Integer
	 */
	Integer savePrize(Prize prize);

	/**
	 * 
	 * @description: <p class="detail">根据id删除奖品</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-上午10:39:10
	 * @param @param id
	 * @param @return
	 * @return Integer
	 */
	Integer delPrizeById(Long id);

	/**
	 * 
	 * @description: <p class="detail">查询奖品列表</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-上午10:56:44
	 * @param @return
	 * @return List<Prize>
	 */
	List<Prize> selectAllPrize();

	/**
	 * 
	 * @description: <p class="detail">添加奖品等级配置</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-下午2:18:46
	 * @param @param prizeLevel
	 * @param @return
	 * @return Integer
	 */
	Integer addPrizeLevel(PrizeLevel prizeLevel);

	/**
	 * 
	 * @description: <p class="detail">根据奖品等级查询</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月19日-下午8:16:27
	 * @param @param prizeLevelId
	 * @param @return
	 * @return Integer
	 */
	Integer selectPrizeRecordByPrizeLevelId(UsersVo usersVo);

	/**
	 * 
	 * @description: <p class="detail">根据奖项id查询奖品id</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月20日-上午10:59:19
	 * @param @param prizeLevelId
	 * @param @return
	 * @return Long
	 */
	Long selectPrizeIdByPrizeLevelId(Long prizeLevelId);

	/**
	 * 
	 * @description: <p class="detail">查询有奖品的奖项</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月20日-下午1:12:07
	 * @param @return
	 * @return List<PrizeLevel>
	 */
	List<PrizeLevel> selectPrizeLevelWithPrize();

	/**
	 * 
	 * @description: <p class="detail">根据奖项id获取奖品图片</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2018年1月15日-上午9:37:32
	 * @param @param prizeLevelId
	 * @param @return
	 * @return String
	 */
	String getPrizePicByPrizeLevelId(Long prizeLevelId);

}
