package com.uhomed.entrance.core.utils;

import java.util.List;

/**
 * 分页对象
 * @author Administrator
 */
public class PageModel<T> {
	
	private List<T> datas;
	
	private int count;
	/** 每页数量 */
	private Integer pageSize;
	/** 当前页 */
	private Integer currPage;
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
