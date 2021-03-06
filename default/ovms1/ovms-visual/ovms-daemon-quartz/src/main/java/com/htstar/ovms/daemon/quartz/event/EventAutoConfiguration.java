package com.htstar.ovms.daemon.quartz.event;

import com.htstar.ovms.daemon.quartz.config.OvmsQuartzInvokeFactory;
import com.htstar.ovms.daemon.quartz.service.SysJobLogService;
import com.htstar.ovms.daemon.quartz.util.TaskInvokUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author frwcloud
 * @date 2018/6/28
 * <p>
 * 多线程自动配置
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class EventAutoConfiguration {
	@Autowired
	private TaskInvokUtil taskInvokUtil;
	@Autowired
	private SysJobLogService sysJobLogService;

	@Bean
	public SysJobListener sysJobListener() {
		return new SysJobListener(taskInvokUtil);
	}

	@Bean
	public OvmsQuartzInvokeFactory ovmsQuartzInvokeFactory(ApplicationEventPublisher publisher) {
		return new OvmsQuartzInvokeFactory(publisher);
	}

	@Bean
	public SysJobLogListener sysJobLogListener() {
		return new SysJobLogListener(sysJobLogService);
	}

	@Bean
	public TaskInvokUtil taskInvokUtil(ApplicationEventPublisher publisher) {
		return new TaskInvokUtil(publisher);
	}

}
