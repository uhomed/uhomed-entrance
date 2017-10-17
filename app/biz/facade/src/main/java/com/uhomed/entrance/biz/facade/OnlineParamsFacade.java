package com.uhomed.entrance.biz.facade;

import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.model.OnlineParams;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public interface OnlineParamsFacade {
	
	/**
	 * 添加在线参数
	 * 
	 * @param code
	 * @param value
	 * @return
	 */
	Result<String> createOnlineParams(String group, String code, String value, String remark);
	
	/**
	 * 更新在线参数
	 * 
	 * @param code
	 * @param value
	 * @param remark
	 * @return
	 */
	Result<String> updateOnlineParams(String group, String code, String value, String remark);
	
	/**
	 * 删除
	 * 
	 * @param code
	 * @return
	 */
	Result<String> deleteOnlineParams(String group, String code);
	
	/**
	 * 查询在线参数分页
	 * 
	 * @param searchKey
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageModel<OnlineParams> queryOnlineParamsByPage(String group, String searchKey, Integer currPage, Integer pageSize);
	
	/**
	 * 查询参数详情
	 * 
	 * @param code
	 * @return
	 */
	OnlineParams queryOnlineParamsByCode(String group, String code);

    /**
     * 批量查询在线参数
     * @param group
     * @param paramCodes 逗号分隔
     * @return
     */
	Map<String,String> queryOnlineParamsByPage(String group, String paramCodes);


	void initCache();


	
}
