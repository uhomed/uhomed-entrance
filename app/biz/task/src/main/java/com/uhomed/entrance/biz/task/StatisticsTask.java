package com.uhomed.entrance.biz.task;

import com.uhomed.entrance.biz.cache.local.StatisticsCache;
import com.uhomed.entrance.biz.service.MethodStatisticsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Component
public class StatisticsTask {
	
	@Resource
	private MethodStatisticsService methodStatisticsService;
	
	@Scheduled(cron = "0/10 * *  * * ? ")
	public void statistics() {
		
		if (StatisticsCache.size() == 0) {
			return;
		}
		
		this.methodStatisticsService.batchCreate( StatisticsCache.get() );
		StatisticsCache.clear();
	}
}
