package com.wpg.projectversion;


//import com.wpg.tools.redis.config.EnableToolsRedis;
//import com.wpg.tools.redis.lock.EnableToolsLock;
import com.wpg.common.mybatis.config.mysql.EnableMyBatisPage;
import com.wpg.tools.log.annotation.EnableAuditLog;
import com.wpg.tools.redis.config.EnableToolsRedis;
import com.wpg.tools.redis.lock.EnableToolsLock;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

/**
 * demo
 */
//启动 springboot ,服务发现，容错功能
@SpringCloudApplication
//读取nacos配置
@RefreshScope
// 开启swagger
@EnableSwagger2
// 开启MP 分页插件
@EnableMyBatisPage
@MapperScan("com.wpg.projectversion.mapper")
// 启动redis
@EnableToolsRedis
// 扫描 feign包
@EnableFeignClients(basePackages = {"com.wpg.**.service.feign"})
// 启动分布式锁
@EnableToolsLock
// 开启审计日志
@EnableAuditLog
public class ProjectVersionManagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProjectVersionManagerApplication.class, args);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("100MB"); //KB,MB
		// 设置总上传数据总大小
		factory.setMaxRequestSize("100MB");
		return factory.createMultipartConfig();
	}

	@Bean
	public Encoder multipartFormEncoder() {
		return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {
			@Override
			public HttpMessageConverters getObject() throws BeansException {
				return new HttpMessageConverters(new RestTemplate().getMessageConverters());
			}
		}));
	}
}
