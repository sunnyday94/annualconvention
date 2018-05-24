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
 * File Created @ [2017年12月11日, 下午6:12:31 (CST)]
 */
package com.chunmi.annualconvention.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chunmi.annualconvention.controller.annotations.Api;
import com.chunmi.annualconvention.dao.PrizeRecordMapper;
import com.chunmi.annualconvention.dao.UsersMapper;
import com.chunmi.annualconvention.po.PictureConfig;
import com.chunmi.annualconvention.po.Prize;
import com.chunmi.annualconvention.po.PrizeLevel;
import com.chunmi.annualconvention.po.Users;
import com.chunmi.annualconvention.service.PictureConfigService;
import com.chunmi.annualconvention.service.PrizeService;
import com.chunmi.annualconvention.service.UsersService;
import com.chunmi.annualconvention.utils.Constant;
import com.chunmi.annualconvention.utils.MessageExceptionEnum;
import com.chunmi.annualconvention.utils.PageBean;
import com.chunmi.annualconvention.utils.PageUtil;
import com.chunmi.annualconvention.utils.Response;
import com.chunmi.annualconvention.utils.qiniu.UploadFileUtil;
import com.chunmi.annualconvention.vo.UsersVo;

@Controller
public class PrizeController {

	/**
	 * 日志
	 */
	private Logger logger  = LoggerFactory.getLogger(PrizeController.class);
	
	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private PrizeRecordMapper prizeRecordMapper;
	
	@Autowired
	private PictureConfigService  pictureConfigService;
	
	
	
	/**
	 * 
	 * @description: <p class="detail">奖品列表页</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-上午10:52:43
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/prizelist")
	public String goToPrizeList(HttpServletRequest request,Model model) {
		try {
			List<PrizeLevel> prizeLevelList = prizeService.selectAllPrizeLevel();
			List<Prize> prizeList = prizeService.selectAllPrize();
			model.addAttribute(Constant.LOGIN_MANAGER,request.getSession().getAttribute(Constant.LOGIN_MANAGER));
			model.addAttribute("prizeLevelList", prizeLevelList);
			model.addAttribute("prizeList", prizeList);
			return "prize/prizelist";
		} catch (Exception e) {
			logger.error("查询数据失败",e);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">添加奖品</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-上午9:28:56
	 * @param @param prize
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/addPrize")
	public String savePrize(Prize prize) {
		try {
			prizeService.savePrize(prize);
			return "redirect:/prizelist";
		} catch (Exception e) {
			logger.error("添加奖品失败",e);
		}
		return null;
	}
	
	/**
	 * 
	 * @description: <p class="detail">根据id删除奖品</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-上午10:36:08
	 * @param @param id
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/delPrizeById")
	@ResponseBody
	public Response delPrizeById(@RequestParam("id") Long id) {
		try {
			prizeService.delPrizeById(id);
			return Response.getSuccess();
		} catch (Exception e) {
			logger.error("删除奖品失败!",e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">添加奖品等级信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-下午2:10:58
	 * @param @param prizeLevel
	 * @param @return
	 * @return String
	 */
	@PostMapping(value="/addPrizeLevel")
	public String addPrizeLevel(PrizeLevel prizeLevel) {
		try {
			prizeService.addPrizeLevel(prizeLevel);
			return "redirect:/prizelist";
		} catch (Exception e) {
			logger.error("添加奖品等级失败",e);
		}
		return null;
	}
 
	
	@GetMapping(value="/danmu")
	public String danmu(HttpServletRequest request,Model model) {
		model.addAttribute(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		PictureConfig  picture =  pictureConfigService.selectByType("2");
		model.addAttribute("picture", picture);
		return "danmu";
	}
	
	
	
	/**
	 * 
	 * @description: <p class="detail">上传奖品图片</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月12日-下午3:30:38
	 * @param @param file
	 * @param @param request
	 * @param @return
	 * @return Response
	 */
	@Api
	@PostMapping(value="/uploadPrizePic")
	@ResponseBody
	public Response uploadPrizePic(MultipartFile file,HttpServletRequest request) {
		String result = "";
		try {
			result = UploadFileUtil.uploadFile(file, request);
			if(result !=null && !result.equals(""))
				return Response.getSuccess(result);
		} catch (Exception e) {
			logger.error("上传奖品图片失败",e);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">幸运抽奖</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月19日-下午3:30:50
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param usersVo
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="luckyDraw_{pageCurrent}_{pageSize}_{pageCount}")
	public String goToLuckyDraw(HttpServletRequest request,Model model,@PathVariable("pageCurrent")Integer pageCurrent,
			@PathVariable("pageSize")Integer pageSize,@PathVariable("pageCount")Integer pageCount,
			UsersVo usersVo,String flag) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
			//查询奖品等级(该奖项下有奖品)
			List<PrizeLevel> prizeLevelList = prizeService.selectPrizeLevelWithPrize(); 
			if(prizeLevelList!=null && prizeLevelList.size()>0)  map.put("prizeLevelList", prizeLevelList);
			//查询中奖者
			PageBean<UsersVo> pb = usersService.selectGetPrizeUsers(usersVo,pageCurrent,pageSize,pageCount,flag); 
			//查询未中奖者
			List<Users> noPrizeUser = usersService.selectAllWithNoPrize();
			//生成新的查询url
			String newUrl = "luckyDraw_{pageCurrent}_{pageSize}_{pageCount}?prizeLevelId="+usersVo.getPrizeLevelId()+
					"&getPrizeNum="+usersVo.getGetPrizeNum();
			//返回分页内容
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			
			map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
			map.put("pb",pb);
			map.put("pageHTML", pageHTML);
			map.put("noPrizeUserCount",noPrizeUser.size());
		} catch (Exception e) {
			logger.error("查询中奖者信息失败",e);
		}
		model.addAllAttributes(map);
		return "prize/luckydraw";
	}
	
	/**
	 * 
	 * @description: <p class="detail">抽奖前进行判断</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月19日-下午8:11:56
	 * @param @param prizeLevelId
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="luckDrawJudge")
	@ResponseBody
	public String luckDrawJudge(UsersVo usersVo) {
		String msg = null;
		try {
			List<Users> noPrizeUser = usersService.selectAllWithNoPrize();
			if(usersVo.getGetPrizeNum()>noPrizeUser.size())
				msg ="剩余未中奖人数为:"+noPrizeUser.size();
		} catch (Exception e) {
			logger.error(e.getMessage());
			msg = "服务器异常";
		}
		return msg;
	}
	
	/**
	 * 
	 * @description: <p class="detail">判断该奖项下是否有奖品</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月20日-上午10:51:02
	 * @param @param prizeLevelId
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="/addPrizeJudge")
	@ResponseBody
	public String addPrizeJudge(@RequestParam("prizeLevelId") Long prizeLevelId) {
		String msg = null;
		try {
			Long prizeId = prizeService.selectPrizeIdByPrizeLevelId(prizeLevelId);
			if(prizeId !=null)
				msg = "该奖项下已存在奖品";
		} catch (Exception e) {
			logger.error(e.getMessage());
			msg = "服务器异常";
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">清空中奖纪录(测试用)</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月20日-下午5:30:51
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/clearAll")
	@ResponseBody
	public Response clearAll() {
		try {
			usersMapper.updateUsersRemark();
			prizeRecordMapper.clearAll();
			//查询未中奖者
			List<Users> noPrizeUser = usersService.selectAllWithNoPrize();
			return Response.getSuccess(noPrizeUser.size());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
 
}
