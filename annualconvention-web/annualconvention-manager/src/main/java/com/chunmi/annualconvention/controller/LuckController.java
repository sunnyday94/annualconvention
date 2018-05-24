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
 * File Created @ [2017年12月21日, 下午1:54:17 (CST)]
 */
package com.chunmi.annualconvention.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chunmi.annualconvention.po.PrizeLevel;
import com.chunmi.annualconvention.po.Users;
import com.chunmi.annualconvention.service.LuckyService;
import com.chunmi.annualconvention.service.PrizeService;
import com.chunmi.annualconvention.service.UsersService;
import com.chunmi.annualconvention.utils.Constant;
import com.chunmi.annualconvention.utils.MessageExceptionEnum;
import com.chunmi.annualconvention.utils.Response;
import com.chunmi.annualconvention.vo.UsersVo;

@Controller
public class LuckController {

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(LuckController.class);
	
	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private LuckyService luckyService;
	
	/**
	 * 
	 * @description: <p class="detail">进入抽奖设置页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月21日-下午1:56:37
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/lucky")
	public String goToLuckySetting(HttpServletRequest request,Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//查询奖品等级(该奖项下有奖品)
			List<PrizeLevel> prizeLevelList = prizeService.selectPrizeLevelWithPrize(); 
			if(prizeLevelList!=null && prizeLevelList.size()>0)  map.put("prizeLevelList", prizeLevelList);
			//查询未中奖者
			List<Users> noPrizeUser = usersService.selectAllWithNoPrize();
			map.put("noPrizeUserCount", noPrizeUser.size());
			map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
			model.addAllAttributes(map);
			return "lucky/luckydrawsetting";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">进入抽奖页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月21日-下午4:29:54
	 * @param @param usersVo
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@PostMapping(value="/goToLuckyDraw")
	public String goToLuckyDraw(UsersVo usersVo,Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//查询未中奖者
			List<Users> noPrizeUser = usersService.selectAllWithNoPrize();
			map.put("joinLuckyDrawUsers", noPrizeUser);
			map.put("prizeLevelId", usersVo.getPrizeLevelId());
			map.put("prizeLevelName", usersVo.getPrizeLevelName().concat("中奖名单"));
			map.put("prizePic",prizeService.getPrizePicByPrizeLevelId(usersVo.getPrizeLevelId()));
			map.put("Lotterynumber", usersVo.getGetPrizeNum());
			model.addAllAttributes(map);
			return "lucky/lucky";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">保存中奖纪录</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月21日-下午5:10:37
	 * @param @param usersVo
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/savePrizeRecord")
	@ResponseBody
	public Response savePrizeRecord(UsersVo usersVo) {
		try {
			luckyService.savePrizeRecord(usersVo);
			return Response.getSuccess();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
}
