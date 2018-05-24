package com.chunmi.annualconvention.service;

import org.springframework.stereotype.Service;

import com.chunmi.annualconvention.utils.Constant;

@Service
public class UserCacheService extends RedisService<String> {

	@Override
	protected String getRedisKey() {
		return Constant.USER_TOKEN;
	}

}
