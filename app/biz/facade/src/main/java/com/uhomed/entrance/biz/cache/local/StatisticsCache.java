package com.uhomed.entrance.biz.cache.local;

import com.uhomed.entrance.model.MethodStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存
 * 
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public class StatisticsCache {
	
	public static List<MethodStatistics> cache = null;
	
	static {
		cache = new ArrayList<>();
	}
	
	public static List<MethodStatistics> get() {
		return cache;
	}
	
	public static void clear() {
		cache.clear();
	}
	
	public static void add(MethodStatistics m) {
		cache.add( m );
	}
	
	public static int size() {
		return cache.size();
	}
	
}
