/**  
 * @Title: BaseDao.java 
 * @author Administrator  
 * @date 2013-7-30 下午01:30:32 
 * @version V1.0   
 */
package com.uhomed.entrance.core.dao.impl;

import java.awt.SystemColor;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.uhomed.entrance.core.utils.PropertyUtil;

/**
 * 基础DAO抽象支持类(用户不直接使用)
 * @version $Id$
 * @since 2.0
 * @date 2013-6-25 下午04:59:42
 * @updateInfo
 */
public abstract class BaseDAOSupport<T> {
	
	
	/** 泛型类 */
	private final Class<T> clazz;
	
	private static final Logger DEFAULT_LOGGER = Logger.getLogger(BaseDAOSupport.class);
	
	@Resource
	protected SqlSessionFactory sqlSessionFactory;
	
	
	
	
	/** 构造函数 */
	public BaseDAOSupport(Class<T> clazz) {
		this.clazz = clazz;
	}

	/** 查找PO */
	public T findPO(Serializable id) throws Exception {
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectOne(clazz.getName() + ".query_id", id);
		} catch (Exception e){
			DEFAULT_LOGGER.error(clazz.getName() + ".query_id error,",e);
		} finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return null;
	}

	/** 创建PO */
	public Serializable createPO(T t) throws Exception {
		return this.createPO(t, "add");
	}
	
	/** 返回主键 */
	public int createPO(T t,String sqlId) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.insert(this.clazz.getName() + "." + sqlId, t);
		} catch (Exception e){
			DEFAULT_LOGGER.error(clazz.getName() + ".add error,",e);
		} finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return -1;
	}

	/** 删除PO */
	public int deletePO(T t) throws Exception {
		return this.deletePO(t, "delete");
	}
	
	public int deletePO(T t,String sqlId) throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.delete(this.clazz.getName() + "." + sqlId, t);
		} catch (Exception e){
			DEFAULT_LOGGER.error(clazz.getName() + ".delete error,",e);
		} finally{
			if (sqlSession != null){
				sqlSession.close();
			}
		}
		return -1;
	}
	
	public int deletePO(Map<String, Object> params) throws Exception {
		if (params == null) {
			params = new HashMap<String,Object>();
		}
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.delete(this.clazz.getName() + "." + "delete_map", params);
		} catch (Exception e){
			DEFAULT_LOGGER.error(clazz.getName() + ".deletePO error,",e);
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return -1;
	}

	/** 更新PO */
	public int updatePO(T t) throws Exception {
		return this.updatePO(t, "update");
	}
	
	public int updatePO(T t ,String sqlId) throws Exception{
//		return sqlMapClient.update(t.getClass().getName() + "." +sqlId, t);
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.update(this.clazz.getName() + "." + sqlId, t);
		} catch (Exception e){
			DEFAULT_LOGGER.error(clazz.getName() + "." + sqlId +" error,",e);
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return -1;
	}

	/** 分页 */
	public List<T> queryByPage(Map<String, Object> params,Integer currPage,Integer pageSize) throws Exception {
		return this.queryByPage(params, "query", currPage, pageSize);
	}
	
	/** 调用sql */
	public List<T> queryByPage(Map<String,Object> params,String sqlId,Integer currPage,Integer pageSize) throws Exception{
		long start = System.currentTimeMillis();
		if (params == null) {
			params = new HashMap<String,Object>();
		}
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			if(currPage == -1 && pageSize == -1){
				return sqlSession.selectList(this.clazz.getName() + "." + sqlId, params);
			}else{
				RowBounds row = new RowBounds(pageSize * (currPage-1), pageSize);
				return sqlSession.selectList(this.clazz.getName() + "." + sqlId, params, row);
			}
		}catch (Exception e){
			DEFAULT_LOGGER.error(clazz.getName() + ".query error,",e);
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		long diff = System.currentTimeMillis() - start;
		if(diff > 200){
			DEFAULT_LOGGER.error( "sql " + this.clazz.getName() + "." + sqlId + " execute time[" + diff + "ms]"  );
		}
		return null;
	}
	
	/** 查询总数 */
	public Integer queryByCount(Map<String,Object> params) throws Exception {
		return this.queryByCount(params, "count");
	}
	
	public Integer queryByCount(Map<String, Object> params, String sqlName) throws Exception {
		if (params == null) {
			params = new HashMap<String,Object>();
		}
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectOne(this.clazz.getName() + "." + sqlName, params);
		}catch (Exception e){
			DEFAULT_LOGGER.error(this.clazz.getName() + "." + sqlName + " error",e);
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return null;
	}
	
	public Serializable queryByDual() throws Exception{
		SqlSession sqlSession = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			return sqlSession.selectOne(this.clazz.getName() + ".dual");
		}catch (Exception e){
			DEFAULT_LOGGER.error(this.clazz.getName() + ".dual error",e);
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return null;
	}
	
	public T queryByPO(Map<String,Object> params) throws Exception{
		List<T> ts = this.queryByPage(params, 1, 1);
		if(CollectionUtils.isNotEmpty(ts)){
			return ts.get(0);
		}
		return null;
	}
	
	
	/** 批量创建po */
	public int batchCreatePO(List<T> list) throws Exception{
		int result = 1;
		SqlSession sqlSession = null;
		try{
			sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			for(T t : list){
				if (PropertyUtil.containsField(t.getClass(), "createTime")) {
					PropertyUtil.setProperty(t, "createTime", new Date());
				}
				if (PropertyUtil.containsField(t.getClass(), "modifyTime")) {
					PropertyUtil.setProperty(t, "modifyTime", new Date());
				}
//				this.createPO(t);
				sqlSession.insert(this.clazz.getName() + ".add", t);
			}
			sqlSession.commit();
		} catch (Exception e){
			result = 0;
			sqlSession.rollback();
			DEFAULT_LOGGER.error(this.clazz.getName() + ".batchCreatePO error",e);
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return result;
	}
	
	/** 批量更新po */
	public int batchUpdatePO(List<T> list) throws Exception{
		int result = 1;
		SqlSession sqlSession = null;
		try{
			sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			for(T t : list){
				if (PropertyUtil.containsField(t.getClass(), "modifyTime")) {
					PropertyUtil.setProperty(t, "modifyTime", new Date());
				}
//				this.createPO(t);
//				this.updatePO(t);
				sqlSession.update(this.clazz.getName() + ".update", t);
			}
			sqlSession.commit();
		}catch (Exception e){
			result = 0;
			sqlSession.rollback();
			DEFAULT_LOGGER.error(this.clazz.getName() + ".batchUpdatePO error",e);
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return result;
	}
	
	/** 批量删除po */
	public int batchDeletePO(List<T> list) throws Exception{
		int result = 1;
		
		SqlSession sqlSession = null;
		
		try{
			sqlSession = this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			for(T t: list){
				sqlSession.delete(this.clazz.getName() + ".delete", t);
			}
			sqlSession.commit();
		} catch (Exception e){
			result = 0;
			sqlSession.rollback();
			DEFAULT_LOGGER.error(this.clazz.getName() + ".batchDeletePO error",e);
		} finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		
		return result;
	}
	
	public String queryByDualForString() throws Exception{
	    SqlSession sqlSession = null;
        try{
            sqlSession = sqlSessionFactory.openSession();
            return sqlSession.selectOne(this.clazz.getName() + ".dual");
        }catch (Exception e){
            DEFAULT_LOGGER.error(this.clazz.getName() + ".dual" + " error",e);
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return null;
	}

}
