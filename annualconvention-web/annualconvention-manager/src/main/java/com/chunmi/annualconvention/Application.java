package com.chunmi.annualconvention;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


/***
 * 
 * @SpringBootApplication，这个注解的作用相当于同时添加了
 * @EnableAutoConfiguration
 * @ComponentScan
 * @Configuration
 */
@SpringBootApplication(
		exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class }, 
		scanBasePackages = {"com.chunmi.annualconvention.*" })
@MapperScan(basePackages= {"com.chunmi.annualconvention.dao"})
@EnableTransactionManagement
@EnableWebSocket
@Slf4j
public class Application extends SpringBootServletInitializer {


	public static void main(String[] args) throws Exception {
		log.debug("application start .....");
		configureApplication(new SpringApplicationBuilder()).run(args);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);//.bannerMode(Mode.OFF);
	}
}
