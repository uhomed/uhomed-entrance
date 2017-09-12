package com.uhomed.entrance.core.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.uhomed.entrance.core.dao.BaseDAO;
import com.uhomed.entrance.core.service.BaseService;
import com.uhomed.entrance.core.utils.PropertyUtil;


public class BaseServiceImpl<P> implements BaseService<P>{
    
    private BaseDAO<P> baseDAO;
    
    /** log4j */
    private final static Logger LOGGER = Logger.getLogger(BaseServiceImpl.class);
    
    public Serializable create(P vo) {
        try {
            return this.baseDAO.createPO(vo);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    public Integer delete(P vo,boolean soft) {
        try {
            if(soft){
                //判断是否存在软删除字段
                if (PropertyUtil.containsField(vo.getClass(), "isDel")) {
                    PropertyUtil.setProperty(vo, "isDel", 1);
                }
                return this.baseDAO.updatePO(vo);
            }
            return this.baseDAO.deletePO(vo);
        } catch (Exception e) {
            LOGGER.error("删除数据失败！",e);
        }
        return -1;
    }

    public P findById(Serializable id) {
        try {
            return this.baseDAO.findPO(id);
        } catch (Exception e) {
            LOGGER.error("查询单条数据失败！",e);
        }
        return null;
    }

    public Integer queryByCount(Map<String, Object> params) {
        try {
            return this.baseDAO.queryByCount(params);
        } catch (Exception e) {
            LOGGER.error("查询总数失败！",e);
        }
        return null;
    }

    public List<P> queryByPage(Map<String, Object> params, int currPage,int pageSize) {
        try {
            return this.baseDAO.queryByPage(params, currPage, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询分页失败！",e);
        }
        return null;
    }

    public Integer update(P vo) {
        try {
            return this.baseDAO.updatePO(vo);
        } catch (Exception e) {
            LOGGER.error("更新数据失败！",e);
        }
        return -1;
    }
    
    public int restoreDel(P vo){
//        P pojo = this.dozerBeanUtil.convert(vo, pojoClazz);
        // 判断是否存在软删除字段
        try {
            if (PropertyUtil.containsField(vo.getClass(), "isDel")) {
                PropertyUtil.setProperty(vo, "isDel", 0);
                return this.baseDAO.updatePO(vo);
            }
            return -1;
        } catch (IllegalAccessException e) {
            LOGGER.error("恢复删除数据失败！",e);
        } catch (InvocationTargetException e) {
            LOGGER.error("恢复删除数据失败！",e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("恢复删除数据失败！",e);
        } catch (Exception e) {
            LOGGER.error("恢复删除数据失败！",e);
        }
        return -1;
    }

    public void setBaseDao(BaseDAO<P> baseDAO) {
        this.baseDAO = baseDAO;
    }
    
    public int batchCreate(List<P> vos) {
        try {
            return this.baseDAO.batchCreatePO(vos);
        } catch (Exception e) {
            LOGGER.error("批量添加数据失败！",e);
        }
        return -1;
    }

    /**
     * @param params
     * @return
     * @see com.uhomed.entrance.core.service.BaseService#delete(java.util.Map)
     */
    public int delete(Map<String, Object> params) {
        try {
            return this.baseDAO.deletePO(params);
        } catch (Exception e) {
            LOGGER.error("删除数据失败！",e);
        }
        return -1;
    }

    /**
     * 调用指定sql查询分页
     * @param params
     * @param sqlId
     * @param currPage
     * @param pageSize
     * @return
     * @see com.uhomed.entrance.core.service.BaseService#queryByPage(java.util.Map, java.lang.String, int, int)
     */
    public List<P> queryByPage(Map<String, Object> params, String sqlId,int currPage, int pageSize) {
        try {
            return this.baseDAO.queryByPage(params,sqlId, currPage, pageSize);
        } catch (Exception e) {
            LOGGER.error("查询分页失败！",e);
        }
        return null;
    }

    /**
     * 调用指定sql查询总数
     * @param params
     * @param sqlId
     * @return
     * @see com.uhomed.entrance.core.service.BaseService#queryByCount(java.util.Map, java.lang.String)
     */
    public Integer queryByCount(Map<String, Object> params, String sqlId) {
        try {
            return this.baseDAO.queryByCount(params, sqlId);
        } catch (Exception e) {
            LOGGER.error("查询总数失败！",e);
        }
        return -1;
    }

    /**
     * @param vos
     * @return
     * @see com.uhomed.entrance.core.service.BaseService#batchUpdate(java.util.List)
     */
    public int batchUpdate(List<P> vos) {
        try {
            return this.baseDAO.batchUpdatePO(vos);
        } catch (Exception e) {
            LOGGER.error("批量修改数据失败！",e);
        }
        return -1;
    }

    /** 
     * @see com.uhomed.entrance.core.service.BaseService#queryByPO(java.util.Map)
     */
    @Override
    public P queryByPO(Map<String, Object> params) {
        try {
            return this.baseDAO.queryByPO(params);
        } catch (Exception e) {
            LOGGER.error("查询单条数据失败！",e);
        }
        return null;
    }

    /** 
     * @see com.uhomed.entrance.core.service.BaseService#batchDelete(java.util.List)
     */
    @Override
    public int batchDelete(List<P> vos) {
        try {
            return this.baseDAO.batchDeletePO(vos);
        } catch (Exception e) {
            LOGGER.error("批量删除数据失败！",e);
        }
        return -1;
    }

    /** 
     * @see com.uhomed.entrance.core.service.BaseService#queryDual()
     */
    @Override
    public String queryDual() {
        try {
            return this.baseDAO.queryByDualForString();
        } catch (Exception e) {
            LOGGER.error("查询序列！",e);
        }
        return null;
    }

}