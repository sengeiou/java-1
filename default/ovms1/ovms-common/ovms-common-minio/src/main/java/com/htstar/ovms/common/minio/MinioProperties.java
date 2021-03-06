package com.htstar.ovms.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * minio 配置信息
 *
 * @author ovms
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
	/**
	 * minio 服务地址 http://ip:port
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String accessKey;

	/**
	 * 密码
	 */
	private String secretKey;

}
