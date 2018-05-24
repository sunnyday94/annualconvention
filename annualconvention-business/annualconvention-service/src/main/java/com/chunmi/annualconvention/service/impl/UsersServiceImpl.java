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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chunmi.annualconvention.dao.PrizeMapper;
import com.chunmi.annualconvention.dao.PrizeRecordMapper;
import com.chunmi.annualconvention.dao.UsersMapper;
import com.chunmi.annualconvention.po.PrizeRecord;
import com.chunmi.annualconvention.po.Users;
import com.chunmi.annualconvention.service.UsersService;
import com.chunmi.annualconvention.utils.PageBean;
import com.chunmi.annualconvention.utils.PageRequest;
import com.chunmi.annualconvention.vo.UsersVo;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {
	
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private PrizeRecordMapper prizeRecordMapper;
	
	@Autowired
	private PrizeMapper prizeMapper;

	@Override
	public int insertSelective(Users record) {
		return usersMapper.insertSelective(record);
	}

	@Override
	public List<Users> selectAll() {
		return usersMapper.selectAll();
	}

	@Override
	public PageBean<UsersVo> selectUserListByCondition(UsersVo usersVo,Integer pageCurrent, Integer pageSize,
			Integer pageCount) {
		PageBean<UsersVo> pb = new PageBean<UsersVo>();
		//判断
		if(pageSize == 0) pageSize = 12;         //每页数据条目数
		if(pageCurrent == 0) pageCurrent = 1;    //当前页
		Integer rows = usersMapper.selectUserCountsByCondition(usersVo).intValue();  //总条目数
		pb.setRows(rows);   //设置总条目数
		//计算分页
		pageCount = rows%pageSize == 0 ? (rows/pageSize) : (rows/pageSize) + 1;
		//设置分页数
		pageCount = pageCount ==0 ? 1: pageCount;  
		//如果当前页>=最大页,则设置当前页为最大页
		if(pageCurrent>=pageCount) {
			pb.setPageCurrent(pageCount);
		}
		PageRequest pageRequest = new PageRequest((pageCurrent-1) * pageSize,pageSize);
		pb.setPageCurrent(pageCurrent);  //当前页
		pb.setPageSize(pageSize);       //每页显示条目
		pb.setPageCount(pageCount);     //总页数
		pb.setObjectBean(usersVo);
		List<UsersVo> userList = usersMapper.selectUserListByCondition(usersVo,pageRequest);
		pb.setList(userList);
		return pb;
	}

	@Override
	public Integer updateUserInfo(UsersVo usersVo) {
		return usersMapper.updateUserInfo(usersVo);
	}

	@Override
	public PageBean<UsersVo> getExportUsersByCondition(UsersVo usersVo) {
		PageBean<UsersVo> pb = new PageBean<UsersVo>();
		List<UsersVo> userList = usersMapper.getExportUsersByCondition(usersVo);
		pb.setList(userList);
		return pb;
	}

	@SuppressWarnings("resource")
	@Override
	public void exportUserInfo(List<UsersVo> pbList, HttpServletResponse response) {
		// 第一步 创建一个webbook,对应一个excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
	    // 第二步 在webbook中添加sheet，对应excel中的sheet
		HSSFSheet sheet = wb.createSheet("用户信息");
	    // 第三步 在sheet中添加表头第0行，此处需要注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
	    // 第四步 创建单元格样式
		HSSFCellStyle style = wb.createCellStyle();

	    HSSFFont hssfFont = wb.createFont(); // 创建字体样式
		hssfFont.setFontName("仿宋_GB2312"); // 仿宋
	    style.setFont(hssfFont); // 设置字体样式
					
	    // 设置宽度
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 20000); //此列为邮寄地址，列宽设置较长
		sheet.setColumnWidth(6, 5000);
		
		//第五步 创建单元格
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("工号");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("奖项");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("电话号码");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("邮寄地址");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("奖品");
		cell.setCellStyle(style);
		
		OutputStream outputStream = null;
		try {
			for(int i =0;i<pbList.size();i++){
				UsersVo user = pbList.get(i);
				row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(user.getId());
				row.createCell(1).setCellValue(user.getJobNum());
				row.createCell(2).setCellValue(user.getPrizeLevelName());
				row.createCell(3).setCellValue(user.getUserName());
				row.createCell(4).setCellValue(user.getTelNum());
				row.createCell(5).setCellValue(user.getAddress());
				row.createCell(6).setCellValue(user.getPrizeName());
			}
			outputStream = response.getOutputStream();
			wb.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.getMessage();
		}finally{
			try {
				if(outputStream!=null){
					outputStream.close();
				}
			} catch (Exception e2) {
				e2.getMessage();
			}
		}		
	}

	@Override
	public Users selectUserByWechat(String wechatNumber) {
		return usersMapper.selectUserByWechat(wechatNumber);
	}

	@Override
	public Long queryCount() {
		return usersMapper.queryCount();
	}

	@Override
	public PageBean<UsersVo> selectGetPrizeUsers(UsersVo usersVo, Integer pageCurrent, Integer pageSize,
			Integer pageCount,String flag) {
		PageBean<UsersVo> pb = new PageBean<UsersVo>();
		List<Users> noPrizeUesrs = this.selectAllWithNoPrize();   //所有参加抽奖人(没有抽到奖品的人)
		if(pageCurrent == 0) pageCurrent = 1;    //当前页
		if(pageSize == 0) pageSize = 12;         //每页数据条目数
		pb.setPageCurrent(pageCurrent);
		pb.setPageSize(pageSize);
		if(usersVo.getPrizeLevelId()==null || usersVo.getPrizeLevelId()==0) {
			pb.setPageCount(0);
			pb.setObjectBean(usersVo);
			pb.setList(null);
			return pb;
		}
		//抽奖Start
		if(flag !=null && flag.equals("1") && noPrizeUesrs.size()>0) {
			Set<Long> getPrizeUserId = new HashSet<Long>();   //中奖者id
			List<PrizeRecord> prizeRecordList = new ArrayList<PrizeRecord>();
			Long prizeId = prizeMapper.selectPrizeIdByPrizeLevelId(usersVo.getPrizeLevelId()); //得到奖品id
			while(getPrizeUserId.size()<usersVo.getGetPrizeNum()) {
				int index = (int) (Math.random() * noPrizeUesrs.size());
				getPrizeUserId.add(noPrizeUesrs.get(index).getId());
			}
			usersMapper.updateUserPrizeStatus(getPrizeUserId);
			Iterator<Long> it = getPrizeUserId.iterator();
			while(it.hasNext()) {
				PrizeRecord  prizeRecord = new PrizeRecord();
				prizeRecord.setPrizeId(prizeId);
				prizeRecord.setUserId(it.next());
				prizeRecordList.add(prizeRecord);
			}
			prizeRecordMapper.insertPrizeRecordList(prizeRecordList);
		}
		//抽奖End
		
		//计算分页
		pageCount = usersVo.getGetPrizeNum()%pageSize == 0 ? (usersVo.getGetPrizeNum()/pageSize) : (usersVo.getGetPrizeNum()/pageSize) + 1;
		//设置分页数
		pageCount = pageCount ==0 ? 1: pageCount;  
		//如果当前页>=最大页,则设置当前页为最大页
		if(pageCurrent>=pageCount) {
			pb.setPageCurrent(pageCount);
		}
		PageRequest pageRequest = new PageRequest((pageCurrent-1) * pageSize,pageSize);
		pb.setPageCurrent(pageCurrent);  //当前页
		pb.setPageSize(pageSize);       //每页显示条目
		pb.setPageCount(pageCount);     //总页数
		pb.setObjectBean(usersVo);
		List<UsersVo> userList = usersMapper.selectGetPrizeUsers(usersVo,pageRequest);
		pb.setList(userList);
		return pb;
	}

	@Override
	public List<Users> selectAllWithNoPrize() {
		return usersMapper.selectAllWithNoPrize();
	}

	@Override
	public Integer addUserInfo(Users users) {
		return usersMapper.insertSelective(users);
	}

	@Override
	public Integer existsUsers(String jobNum) {
		return usersMapper.existsUsers(jobNum);
	}

	@Override
	public Users selectUserByNum(String jobNum) {
		return usersMapper.selectUserByNum(jobNum);
	}
	
}
