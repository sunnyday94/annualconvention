package com.chunmi.annualconvention.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chunmi.annualconvention.controller.annotations.Api;
import com.chunmi.annualconvention.po.PictureConfig;
import com.chunmi.annualconvention.po.Users;
import com.chunmi.annualconvention.service.JobNumService;
import com.chunmi.annualconvention.service.PictureConfigService;
import com.chunmi.annualconvention.service.UserCacheService;
import com.chunmi.annualconvention.service.UsersService;
import com.chunmi.annualconvention.utils.MessageExceptionEnum;
import com.chunmi.annualconvention.utils.Response;
 
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger logger  = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PictureConfigService pictureConfigService;
	
	@Autowired
	private UserCacheService userCacheService;
	
	@Autowired
	private JobNumService jobNumService;
	
	/**
	 * 
	 * @description: <p class="detail">注册用户</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年12月14日-上午10:14:38
	 * @param @return
	 * @return Response
	 */
	@Api
	@PostMapping(value = "/register")
	public Response register(@RequestBody Users user, @RequestHeader("usertoken") String token) {

		try {
			String jobNum = user.getJobNum();
			if (StringUtils.isNoneBlank(jobNum)) {
				jobNum = jobNum.toUpperCase();
				user.setJobNum(jobNum);
			}
			Integer num = jobNumService.existsJobNum(jobNum);
			if (num == null || num == 0) {
				return Response.getError(MessageExceptionEnum.JOB_NUM_NOT_EXISTS);
			}
			Users users = usersService.selectUserByNum(jobNum);
			if (users != null) {
				return Response.getError(MessageExceptionEnum.JOB_NUM_HAS_REGISTERED);
			}
			user.setWechatNumber(userCacheService.get(token));
			usersService.insertSelective(user);
			return Response.getSuccess(user);

		} catch (Exception e) {
			logger.error("服务器开小差了===>", e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	

	/**
	 * 
	 * @description: <p class="detail">获取已签到人数和总人数</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年12月14日-上午10:14:38
	 * @param @return
	 * @return Response
	 */
	@Api
	@RequestMapping(value = "queryUsers", method = RequestMethod.GET)
	public Response queryUsers() {
		Map<String, Object> result = new HashMap<>();
		try {
			List<Users> users = usersService.selectAll();
			Long count = usersService.queryCount();
			result.put("user", users);
			result.put("count", count);

			return Response.getSuccess(result);

		} catch (Exception e) {
			logger.error("服务器开小差了===>", e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	@Api
	@RequestMapping(value = "check-token", method = RequestMethod.GET)
	public Response checkToken(@RequestHeader("usertoken") String token) {
		if (StringUtils.isNotBlank(userCacheService.get(token))) {
			return Response.getSuccess();
		}
		return Response.getError(MessageExceptionEnum.ERROR_TOKEN_EXPIR);

	}

	/**
	 * 
	 * @description: <p class="detail">根据微信号查询用户是注册</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年12月14日-上午10:14:38
	 * @param @return
	 * @return Response
	 */
	@Api
	@RequestMapping(value = "checkRegister", method = RequestMethod.GET)
	public Response whetherSign(@RequestHeader("usertoken") String token) {
		try {
			String wechatNumber = userCacheService.get(token);
			Users users = usersService.selectUserByWechat(wechatNumber);
			if (users == null) {
				return Response.getSuccess();
			} else {
				return Response.buildResult(MessageExceptionEnum.CUSTOMER_HAS_REGISTERED);
			}

		} catch (Exception e) {
			logger.error("服务器开小差了===>", e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
	
	/**
	 * 
	 * @description: <p class="detail">查询app顶部图片</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年12月14日-上午10:14:38
	 * @param @return
	 * @return Response
	 */
	@Api
	@RequestMapping(value = "appTopPic", method = RequestMethod.GET)
	public Response appTopPic() {

		try {
			PictureConfig picture = pictureConfigService.selectByType("0");
			return Response.getSuccess(picture);

		} catch (Exception e) {
			logger.error("服务器开小差了===>", e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}

	/**
	 * 
	 * @description: <p class="detail">查询年会节目表图片</p>
	 * @author: <a href="mailto:zhangqian@chunmi.com ">zhangqian</a>
	 * @date: 2017年12月14日-上午10:14:38
	 * @param @return
	 * @return Response
	 */
	@Api
	@RequestMapping(value = "schedulePic", method = RequestMethod.GET)
	public Response schedulePic() {

		try {
			PictureConfig picture = pictureConfigService.selectByType("1");
			return Response.getSuccess(picture);

		} catch (Exception e) {
			logger.error("服务器开小差了===>", e);
			return Response.getError(MessageExceptionEnum.ERROR_HANDLE);
		}
	}
 
	@Api
	@RequestMapping(value = "wxapp/login", method = RequestMethod.GET)
	public Response wxapplogin(@RequestParam("code") String code) throws IOException {
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxfaf9de0dcda0ae4e&secret=2f6b684ec7c29110c5dfc28af380b538&js_code="
				+ code + "&grant_type=authorization_code";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("User-Agent", "Mozilla/5.0");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		String res = EntityUtils.toString(httpResponse.getEntity());
		logger.info("res: {}", res);

		JSONObject json = JSONObject.parseObject(res);
		if (json != null && json.containsKey("openid")) {
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			userCacheService.put(token, json.getString("openid"), -1);
			return Response.getSuccess(token);
		} else {
			return Response.getError(MessageExceptionEnum.ERROR_CODE);
		}
	}
}
 