package com.htstar.ovms.common.job;

import com.htstar.ovms.common.job.properties.XxlJobProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ovms
 * @date 2019-09-18
 * <p>
 * xxl 初始化
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.htstar.ovms.common.job.properties")
public class XxlJobAutoConfiguration {

	@Bean
	public XxlJobSpringExecutor xxlJobSpringExecutor(XxlJobProperties xxlJobProperties) {
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddresses());
		xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutor().getAppname());
		xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutor().getAddress());
		xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
		xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
		xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getExecutor().getAccessToken());
		xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
		xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());
		return xxlJobSpringExecutor;
	}
}
