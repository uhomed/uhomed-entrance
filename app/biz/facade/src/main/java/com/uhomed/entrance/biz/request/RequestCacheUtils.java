package com.uhomed.entrance.biz.request;

import com.uhomed.entrance.core.utils.DateUtils;
import com.uhomed.entrance.core.utils.sizeof.RamUsageEstimator;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public class RequestCacheUtils {
	
	private static ConcurrentHashMap<String, RequestCache>	CACHE				= null;
	
	// private static final long maxCacheSize = (1024 * 1024 * 1024) * 10;
	private static final long								maxCacheSize		= 1024;
	/** 是否正在回收 */
	private static boolean									GC					= false;
	/** 当前占用byte */
	private static long										currRam				= 0l;
	/** 每多少次检查一次内存 */
	private static int										maxCount			= 1000;
	/** 档期使用计数器 */
	private static int										count				= 0;
	/** 存活最大清除秒数，当清理内存时，该存活时长也会被清理，默认5分钟 */
	private static long										survivalMaxSecond	= 5 * 60;
	
	private static boolean									debug				= true;
	
	private RequestCacheUtils() {
	}
	
	static {
		CACHE = new ConcurrentHashMap<>();
	}
	
	public static void put(String k, RequestCache v) {
		
		CACHE.put( k, v );
		count++;
		
		if (count == maxCount) {
			currRam = RamUsageEstimator.sizeOf( CACHE );
			count = 0;
		}
		if (!GC && currRam > maxCacheSize) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					GC = true;
					if (debug) {
						System.out.println( "缓存gc中" );
					}
					
					for (Map.Entry<String, RequestCache> r : CACHE.entrySet()) {
						if (DateUtils.compareDate( new Date(), r.getValue().getExpiredTime() )
								|| DateUtils.dateDiffSenc( r.getValue().getExpiredTime(),
										r.getValue().getCacheTime() ) > survivalMaxSecond) {
							CACHE.remove( r.getKey() );
						}
					}
					if (debug) {
						System.out.println( "缓存gc完成" );
					}
					
					GC = false;
				}
			};
			Thread thread = new Thread( r );
			thread.start();
		}
		
	}
	
	public static Object get(String k) {
		RequestCache cache = CACHE.get( k );
		if (cache == null) {
			return null;
		}
		if (DateUtils.compareDate( cache.getExpiredTime(), new Date() )) {
			if (debug) {
				System.out.println( "使用缓存" + k );
			}
			
			return cache.getValue();
		} else {
			// 主动删除
			CACHE.remove( k );
			if (debug) {
				System.out.println( "删除缓存" + k );
			}
			return null;
		}
	}
	
}
