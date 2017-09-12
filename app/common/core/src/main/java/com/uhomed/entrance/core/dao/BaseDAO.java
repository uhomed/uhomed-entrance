/**  
 * @Title: BaseDao.java 
 * @author lim  
 * @date 2013-8-1 上午10:15:49 
 * @version V1.0   
 */
package com.uhomed.entrance.core.dao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/** 
 * 基础dao接口
 * @ClassName: BaseDAO 
 * @version 1.0 2013-8-1 上午10:15:49
 * @lastUpdateTime 2013-8-1 上午10:15:49 
 */
public interface BaseDAO<T> {
	
	/**
	 * id查找po
	 * @param id
	 * @return
	 * @lastUpdateTime 2013-6-25 下午04:43:30
	 * @updateInfo
	 */
	T findPO(Serializable id) throws Exception;
	/**
	 * 创建po
	 * @param t
	 * @lastUpdateTime 2013-6-25 下午04:44:11
	 * @updateInfo
	 */
	Serializable createPO(T t) throws Exception;
	
	/**
	 * 删除po
	 * @param t
	 * @lastUpdateTime 2013-6-25 下午04:44:50
	 * @updateInfo
	 */
	int deletePO(T t) throws Exception;
	/**
	 * 批量删除po
	 * @param params
	 * @return
	 * @throws Exception
	 */
	int deletePO(Map<String, Object> params) throws Exception;
	/**
	 * 更新po
	 * @param t
	 * @lastUpdateTime 2013-6-25 下午04:45:12
	 * @updateInfo
	 */
	int updatePO(T t) throws Exception;
	/**
	 * 更新po
	 * @param t
	 * @throws Exception 
	 * @author lim
	 */
	int updatePO(T t,String sqlId) throws Exception;
	/**
	 * 分页查询
	 * @param param
	 * @param currPage
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 * @author lim
	 */
	List<T> queryByPage(Map<String,Object> params,Integer currPage,Integer pageSize) throws Exception;
	/**
	 * 查询总数
	 * @return
	 * @throws Exception 
	 * @author lim
	 */
	Integer queryByCount(Map<String,Object> params) throws Exception;
	
	/**
	 * 指定sql查询总数
	 * @param params
	 * @param sqlName
	 * @return
	 * @throws Exception
	 */
	Integer queryByCount(Map<String, Object> params, String sqlName) throws Exception;
	/**
	 * 执行动态sql
	 * @param params 参数集合
	 * @param sqlName sql名称
	 * @param currPage 当前页
	 * @param pageSize 每页显示条数 -1则全部查询
	 * @return
	 * @throws Exception 
	 * @author lim
	 */
	List<T> queryByPage(Map<String,Object> params,String sqlName,Integer currPage,Integer pageSize) throws Exception;
	
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 * @throws Exception 
	 * @author lim
	 */
	int batchCreatePO(List<T> list) throws Exception;
	/**
	 * 批量更新
	 * @param list
	 * @return
	 * @throws Exception 
	 * @author lim
	 */
	int batchUpdatePO(List<T> list) throws Exception;
	/**
	 * 批量删除
	 * @param list
	 * @return
	 * @throws Exception 
	 * @author lim
	 */
	int batchDeletePO(List<T> list) throws Exception;
	
	/**
	 * 查询对象
	 * @param params
	 * @return
	 */
	T queryByPO(Map<String,Object> params) throws Exception;
	
	String queryByDualForString() throws Exception;
}
