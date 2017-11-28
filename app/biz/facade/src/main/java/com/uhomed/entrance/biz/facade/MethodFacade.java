package com.uhomed.entrance.biz.facade;

import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.model.MethodDubbo;
import com.uhomed.entrance.model.MethodInfo;
import com.uhomed.entrance.model.MethodParam;
import com.uhomed.entrance.view.MethodInfoView;
import com.uhomed.entrance.view.MethodParamView;

import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public interface MethodFacade {
	
	/**
	 * 创建方法
	 * 
	 * @param apiMethodCode
	 * @param apiMethodName
	 * @param apiMethodVersion
	 * @param status
	 * @param verifiSso
	 * @param mode
	 * @param methodDesc
	 * @return
	 */
	Result<Integer> createMethod(String groupCode, String apiMethodCode, String apiMethodName, String apiMethodVersion,
			String status, String verifiSso, String mode, String methodDesc, String type);
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	Result<String> deleteMethod(Integer id);
	
	/**
	 * 修改方法
	 * 
	 * @param id
	 * @param apiMethodCode
	 * @param apiMethodName
	 * @param apiMethodVersion
	 * @param status
	 * @param verifiSso
	 * @param mode
	 * @param methodDesc
	 * @return
	 */
	Result<String> updateMethod(Integer id, String apiMethodCode, String apiMethodName, String apiMethodVersion,
			String status, String verifiSso, String mode, String methodDesc, String type, String classPath,
			String methodName, List<MethodParam> paramList);
	
	/**
	 * @param search
	 * @param likeApiMethodName
	 * @param status
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	PageModel<MethodInfoView> methodList(String groupCode, String search, String likeApiMethodName, String status,
			Integer currPage, Integer pageSize);
	
	/**
	 * 添加dubbo接口
	 * 
	 * @param apiMethodCode
	 * @param apiMethodName
	 * @param apiMethodVersion
	 * @param status
	 * @param verifiSso
	 * @param mode
	 * @param methodDesc
	 * @param classPath
	 * @param methodName
	 * @return
	 */
	Result<Integer> createMethodDubbo(String groupCode, String apiMethodCode, String apiMethodName,
			String apiMethodVersion, String status, String verifiSso, String mode, String methodDesc, String classPath,
			String methodName, List<MethodParam> paramList);
	
	/**
	 * 更新dubbo接口
	 * 
	 * @param id
	 * @param apiMethodCode
	 * @param apiMethodName
	 * @param apiMethodVersion
	 * @param status
	 * @param verifiSso
	 * @param mode
	 * @param methodDesc
	 * @param classPath
	 * @param methodName
	 * @return
	 */
	Result<String> updateMethodDubbo(Integer id, String apiMethodCode, String apiMethodName, String apiMethodVersion,
			String status, String verifiSso, String mode, String methodDesc, String classPath, String methodName);
	
	/**
	 * 批量更新参数
	 * 
	 * @param params
	 * @return
	 */
	Result<String> batchUpdateMethodParam(List<MethodParam> params);
	
	/**
	 * 删除方法中参数
	 * 
	 * @param id
	 * @return
	 */
	Result<String> deleteMethodParam(Integer id);
	
	/**
	 * 主键查询
	 * 
	 * @param id
	 * @return
	 */
	MethodInfo queryById(Integer id);
	
	/**
	 * 初始化缓存
	 */
	void initCache();
	
	MethodDubbo queryMethodDubboById(Integer id);
	
	/**
	 *
	 * @param params
	 * @return
	 */
	Result<Integer> updateParams(List<MethodParam> params);
	
	/**
	 * 获取所有参数
	 * 
	 * @param methodId
	 * @return
	 */
	List<MethodParamView> queryParams(Integer methodId);
	
}
