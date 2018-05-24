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
 * File Created @ [2017年12月13日, 下午1:22:23 (CST)]
 */
package com.chunmi.annualconvention.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chunmi.annualconvention.po.PrizeLevel;
import com.chunmi.annualconvention.po.Users;
import com.chunmi.annualconvention.service.JobNumService;
import com.chunmi.annualconvention.service.PrizeService;
import com.chunmi.annualconvention.service.UsersService;
import com.chunmi.annualconvention.utils.Constant;
import com.chunmi.annualconvention.utils.MessageExceptionEnum;
import com.chunmi.annualconvention.utils.PageBean;
import com.chunmi.annualconvention.utils.PageUtil;
import com.chunmi.annualconvention.utils.Response;
import com.chunmi.annualconvention.vo.UsersVo;

@Controller
public class UserManagerController {
	
	/**
	 * logger日志
	 */
	private Logger logger = LoggerFactory.getLogger(UserManagerController.class);
	
	@Autowired
	private PrizeService prizeService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private JobNumService jobNumService;
	
	
	/**
	 * export导出文件名前缀
	 */
	private final String USERINFO = "userinfo";
	
	
	/**
	 * 
	 * @description: <p class="detail">用户列表</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月13日-下午4:17:13
	 * @param @param request
	 * @param @param model
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @param usersVo
	 * @param @return
	 * @return String
	 */
	@GetMapping(value="userlist_{pageCurrent}_{pageSize}_{pageCount}")
	public String goToUserList(HttpServletRequest request,Model model,@PathVariable("pageCurrent")Integer pageCurrent,
			@PathVariable("pageSize")Integer pageSize,@PathVariable("pageCount")Integer pageCount,
			UsersVo usersVo) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		try {
			List<PrizeLevel> prizeLevelList = prizeService.selectAllPrizeLevel(); //查询奖品等级
			if(prizeLevelList!=null && prizeLevelList.size()>0) map.put("prizeLevelList", prizeLevelList);
			PageBean<UsersVo> pb = usersService.selectUserListByCondition(usersVo,pageCurrent,pageSize,pageCount); //查询用户列表
			map.put("pb",pb);
			//生成新的查询url
			String newUrl = "userlist_{pageCurrent}_{pageSize}_{pageCount}?jobNum="+usersVo.getJobNum()+
					"&userName="+usersVo.getUserName()+"&prizeLevelId="+usersVo.getPrizeLevelId();
			//返回分页内容
			String pageHTML = PageUtil.getPageContent(newUrl,pb.getPageCurrent(), pb.getPageSize(), pb.getPageCount());
			map.put("pageHTML", pageHTML);
		} catch (Exception e) {
			logger.error("查询用户列表失败",e);
		}
		model.addAllAttributes(map);
		return "user/userlist";
	}
	
	/**
	 * 
	 * @description: <p class="detail">修改用户信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月13日-下午7:34:30
	 * @param @param usersVo
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/updateUserInfo")
	@ResponseBody
	public Response updateUserInfo(UsersVo usersVo) {
		try {
			usersService.updateUserInfo(usersVo);
			return Response.getSuccess();
		} catch (Exception e) {
			logger.error("修改用户信息失败",e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">导出用户信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月14日-上午10:50:58
	 * @param @param userVo
	 * @param @param response
	 * @return void
	 */
	@PostMapping(value="/exportUserInfo")
	public void exportUserInfo(UsersVo usersVo,HttpServletResponse response) {
		PageBean<UsersVo> pb = null;
		try {
			pb = usersService.getExportUsersByCondition(usersVo);
			List<UsersVo> pbList = pb.getList();
			if(pbList==null || pbList.isEmpty()){
				throw new RuntimeException("暂无数据，导出失败!");
			}else{
				// 设置下载时客户端Excel的名称
				String filename_prefix = usersVo.getPrizeLevelId() == 0 ? "全部奖项" : usersVo.getPrizeLevelName();
				String filename = USERINFO+"_"+filename_prefix.concat(".xls");
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
				usersService.exportUserInfo(pbList,response);
			}
		} catch (Exception e) {
			logger.error("查询用户数据失败",e);
		}
	}
	
	
	/**
	 * 
	 * @description: <p class="detail">新增用户</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月26日-下午2:12:12
	 * @param @param usersVo
	 * @param @return
	 * @return Response
	 */
	@PostMapping(value="/addUserInfo")
	@ResponseBody
	public Response addUserInfo(Users users) {
		try {
//			Integer count = jobNumService.existsJobNum(users.getJobNum());
//			if(count==0) return Response.getError(MessageExceptionEnum.JOB_NUM_NOT_EXISTS);
			Integer row = usersService.existsUsers(users.getJobNum());
			if(row>0) return Response.getError(MessageExceptionEnum.USER_EXISTED);
			usersService.addUserInfo(users);
			return Response.getSuccess();
		} catch (Exception e) {
			logger.error("新增用户失败");
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
}
