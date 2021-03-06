package com.teenyda;

import com.teenyda.common.Util;
import com.teenyda.controller.file.property.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({
		FileProperties.class
})
// aop
// @EnableAspectJAutoProxy

// @ComponentScan("com.teenyda.dao")

// 解决Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required
@EnableAutoConfiguration
@MapperScan({"com.teenyda.mapping", "com.teenyda.mapping.base", "com.teenyda.dao"})
// 开启事务
@EnableTransactionManagement
public class SpringbootTemplateApplication {

	public static void main(String[] args) {
		//app.setBannerMode(Banner.Mode.OFF);
		SpringApplication.run(SpringbootTemplateApplication.class, args);
		System.out.println(Util.resourcePath());
		System.out.println(Util.lunchAddress());
	}
}

