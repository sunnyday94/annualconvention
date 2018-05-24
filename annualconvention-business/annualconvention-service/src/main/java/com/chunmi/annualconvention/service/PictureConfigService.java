package com.chunmi.annualconvention.service;

import java.util.List;

import com.chunmi.annualconvention.po.PictureConfig;

public interface PictureConfigService {
	
	 int insert(PictureConfig record);
	 
	 PictureConfig selectByType(String type);
	 
	 int delByType(String type);
	 
	 List<PictureConfig> selectAll();

}
