package com.htstar.ovms.monitor.controller;

import com.htstar.ovms.common.core.util.R;
import com.htstar.ovms.monitor.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ovms
 * @date 2019-05-08
 * <p>
 * redis 数据
 */
@RestController
@AllArgsConstructor
@RequestMapping("/redis")
public class RedisController {
	private final RedisService redisService;

	/**
	 * 查询redis信息
	 *
	 * @return
	 */
	@GetMapping("/info")
	public R memory() {
		return R.ok(redisService.getInfo());
	}
}
