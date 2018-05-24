package com.chunmi.annualconvention.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chunmi.annualconvention.po.PictureConfig;
import com.chunmi.annualconvention.service.PictureConfigService;
import com.chunmi.annualconvention.utils.Constant;

 

@Controller
public class PictureConfigController {
	
	private Logger logger = LoggerFactory.getLogger(PictureConfigController.class);
	
	@Autowired
	private PictureConfigService pictureConfigService;
	
	
	@GetMapping(value="/uploadSchedule")
	public String danmu(HttpServletRequest request,Model model) {
		model.addAttribute(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		List<PictureConfig> picList = pictureConfigService.selectAll();
		model.addAttribute("picList", picList);
		return "uploadSchedule";
	}
	
	@PostMapping(value="/addSchedule")
	public String savePrize(PictureConfig pictureConfig) {
		try {
			pictureConfigService.delByType(pictureConfig.getType());
			pictureConfig.setCreateTime(new Date());
			pictureConfigService.insert(pictureConfig);
			return "redirect:/uploadSchedule";
		} catch (Exception e) {
			logger.error("添加失败",e);
		}
		return null;
	}
	
	@GetMapping(value="/danmuTest")
	public String danmuTest(HttpServletRequest request,Model model) {
		model.addAttribute(Constant.LOGIN_MANAGER, request.getSession().getAttribute(Constant.LOGIN_MANAGER));
		return "danmu2";
	}
	
  

}
