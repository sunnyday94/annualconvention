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

import javax.servlet.http.HttpServletResponse;

import com.chunmi.annualconvention.po.Users;
import com.chunmi.annualconvention.utils.PageBean;
import com.chunmi.annualconvention.vo.UsersVo;

public interface UsersService {

	/**
	 * 
	 * @description: <p class="detail">添加用户</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年12月12日-上午10:27:42
	 * @param @return
	 * @return int
	 */
	int insertSelective(Users record);
	
	
	List<Users> selectAll();


	PageBean<UsersVo> selectUserListByCondition(UsersVo usersVo, Integer pageCurrent, Integer pageSize,
			Integer pageCount);


	/**
	 * 
	 * @description: <p class="detail">更新用户信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月13日-下午7:36:38
	 * @param @param usersVo
	 * @param @return
	 * @return Integer
	 */
	Integer updateUserInfo(UsersVo usersVo);

	/**
	 * 
	 * @description: <p class="detail">根据条件查询将导出的用户信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月14日-上午11:01:48
	 * @param @param usersVo
	 * @param @return
	 * @return PageBean<UsersVo>
	 */
	PageBean<UsersVo> getExportUsersByCondition(UsersVo usersVo);


	/**
	 * 
	 * @description: <p class="detail">导出用户信息</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月14日-上午11:07:25
	 * @param @param pbList
	 * @param @param response
	 * @return void
	 */
	void exportUserInfo(List<UsersVo> pbList, HttpServletResponse response);
	
	Users selectUserByWechat(String wechatNumber);
	
	Long queryCount();


	/**
	 * 
	 * @description: <p class="detail">查询获奖者</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月19日-下午3:31:32
	 * @param @param usersVo
	 * @param @param pageCurrent
	 * @param @param pageSize
	 * @param @param pageCount
	 * @param @return
	 * @return PageBean<UsersVo>
	 */
	PageBean<UsersVo> selectGetPrizeUsers(UsersVo usersVo, Integer pageCurrent, Integer pageSize, Integer pageCount,String flag);

	/**
	 * 
	 * @description: <p class="detail">未中奖人数</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月20日-上午10:35:43
	 * @param @return
	 * @return List<Users>
	 */
	List<Users> selectAllWithNoPrize();


	/**
	 * 
	 * @description: <p class="detail">新增用户</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月26日-下午2:15:30
	 * @param @param users
	 * @param @return
	 * @return Integer
	 */
	Integer addUserInfo(Users users);


	/**
	 * 
	 * @description: <p class="detail">根据工号查询用户是否存在</p>
	 * @author: <a href="mailto:sunny@chunmi.com ">sunny</a>
	 * @date: 2017年12月27日-上午11:28:11
	 * @param @param jobNum
	 * @param @return
	 * @return Integer
	 */
	Integer existsUsers(String jobNum);
	
	
	Users selectUserByNum(String jobNum);


 
}
