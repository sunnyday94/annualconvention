package com.chunmi.annualconvention.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chunmi.annualconvention.dao.PictureConfigMapper;
import com.chunmi.annualconvention.po.PictureConfig;
import com.chunmi.annualconvention.service.PictureConfigService;

@Service
public class PictureConfigServiceImpl implements PictureConfigService{
	
	@Autowired
	private PictureConfigMapper pictureConfigMapper;

	@Override
	public int insert(PictureConfig record) {
		return pictureConfigMapper.insert(record);
	}

	@Override
	public PictureConfig selectByType(String type) {
		return pictureConfigMapper.selectByType(type);
	}

	@Override
	public int delByType(String type) {
		return pictureConfigMapper.delByType(type);
	}

	@Override
	public List<PictureConfig> selectAll() {
		return pictureConfigMapper.selectAll();
	}
	
	

 }

  
 