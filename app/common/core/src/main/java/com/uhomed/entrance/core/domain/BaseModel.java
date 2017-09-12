/**  
 * @Title: CommonModel.java 
 * @author lim  
 * @date 2013-8-18 下午07:08:07 
 * @version V1.0   
 */
package com.uhomed.entrance.core.domain;

import java.io.Serializable;
 /**
  * 基础抽象模型
  * @author liming
  * @version $Id$   
  * @since 2.0
  * @date 2013-6-25 下午04:18:18
  * @updateInfo
  */
public abstract class BaseModel implements Serializable {

	 /**    */
	private static final long serialVersionUID = 2267204759783891051L;
	
	/** 软删除0，默认，1，删除 */
	protected Integer isDel;

	/** 
	 * @return isDel 
	 */
	public Integer getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel the isDel to set
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}
