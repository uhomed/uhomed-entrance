/**  
 * @Title: CommonModel.java 
 * @date 2013-8-18 下午07:08:07 
 * @version V1.0   
 */
package com.uhomed.entrance.core.domain;
 /**
  * 域模型抽象父类
  * @author liming
  * @version $Id$   
  * @since 2.0
  * @date 2013-6-25 下午04:17:01
  * @updateInfo
  */
public abstract class AbstractModel extends CommonModel{
	
	private static final long serialVersionUID = -8786909250877113767L;

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
