/**  
 * @Title: CommonModel.java 
 * @author Administrator  
 * @date 2013-8-18 下午07:08:07 
 * @version V1.0   
 */
package com.uhomed.entrance.core.domain;

import java.io.Serializable;
import java.util.Date;

/** 
 * 抽象公用属性
 * @author lim
 * @ClassName: CommonModel 
 * @Company:浙江图讯科技
 * @ProjectName：省局考试系统
 * @version 1.0 2013-8-18 下午07:08:09
 * @lastUpdateTime 2013-8-18 下午07:08:09 
 */
public abstract class CommonModel implements Serializable{

	private static final long serialVersionUID = -889288338209549278L;
	/** 创建时间 */
	protected Date createTime;
	/** 修改时间 */
	protected Date modifyTime;
	/** 
	 * @return createTime 
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
