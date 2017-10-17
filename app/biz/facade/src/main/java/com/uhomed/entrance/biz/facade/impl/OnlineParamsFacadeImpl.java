package com.uhomed.entrance.biz.facade.impl;

import com.uhomed.entrance.biz.cache.local.OnlineParamsCache;
import com.uhomed.entrance.biz.facade.OnlineParamsFacade;
import com.uhomed.entrance.biz.service.OnlineParamsService;
import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.model.OnlineParams;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Service("onlineParamsFacade")
public class OnlineParamsFacadeImpl implements OnlineParamsFacade {
	
	@Resource
	private OnlineParamsService	onlineParamsService;
	
	@Resource
	private OnlineParamsCache	onlineParamsCache;
	
	@Override
	public Result<String> createOnlineParams(String group, String code, String value, String remark) {
		
		Result<String> result = new Result<>();
		
		Map<String, Object> params = new HashMap<>();
		params.put( "groupCode", group );
		params.put( "paramCode", code );
		
		// 检查是否存在
		int count = this.onlineParamsService.queryByCount( params );
		if (count > 0) {
			result.setMessage( "该code已存在！" );
			return result;
		}
		
		OnlineParams onlineParams = new OnlineParams();
		onlineParams.setParamCode( code );
		onlineParams.setParamValue( value );
		onlineParams.setRemark( remark );
		onlineParams.setGroupCode( group );
		count = (Integer) this.onlineParamsService.create( onlineParams );
		if (count > 0) {
			result.setMessage( "添加成功！" );
			result.setSuccess( true );
			this.refreshCache( group, code, value );
		} else {
			result.setMessage( "添加失败，请稍候再试！" );
		}
		
		return result;
	}
	
	@Override
	public Result<String> updateOnlineParams(String group, String code, String value, String remark) {
		Result<String> result = new Result<>();
		OnlineParams onlineParams = new OnlineParams();
		onlineParams.setParamCode( code );
		onlineParams.setParamValue( value );
		onlineParams.setRemark( remark );
		onlineParams.setGroupCode( group );
		int count = this.onlineParamsService.update( onlineParams );
		if (count > 0) {
			result.setMessage( "更新成功！" );
			result.setSuccess( true );
			
			// 刷新缓存
			this.refreshCache( group, code, value );
		} else {
			result.setMessage( "更新失败，请稍候再试！" );
		}
		return result;
	}
	
	@Override
	public Result<String> deleteOnlineParams(String group, String code) {
		
		Result<String> result = new Result<>();
		
		OnlineParams onlineParams = new OnlineParams();
		onlineParams.setParamCode( code );
		onlineParams.setGroupCode( group );
		int count = this.onlineParamsService.delete( onlineParams, false );
		if (count > 0) {
			result.setMessage( "删除成功！" );
			result.setSuccess( true );
		} else {
			result.setMessage( "删除失败，请稍候再试！" );
		}
		return result;
	}
	
	@Override
	public PageModel<OnlineParams> queryOnlineParamsByPage(String group, String searchKey, Integer currPage, Integer pageSize) {
		
		Map<String, Object> params = new HashMap<>();
		params.put( "groupCode", group );
		params.put( "searchKey", searchKey );
		List<OnlineParams> list = this.onlineParamsService.queryByPage( params, currPage, pageSize );
		PageModel<OnlineParams> page = new PageModel<>();
		page.setDatas( list );
		page.setCount( this.onlineParamsService.queryByCount( params ) );
		
		return page;
	}
	
	@Override
	public OnlineParams queryOnlineParamsByCode(String group, String code) {
		Map<String, Object> params = new HashMap<>();
		params.put( "groupCode", group );
		params.put( "paramCode", code );
		
		return this.onlineParamsService.queryByPO( params );
	}
	
	@Override
	public Map<String, String> queryOnlineParamsByPage(String group, String paramCodes) {
		List<String> codes = Arrays.asList( paramCodes.split( "," ) );
		
		// Map<String, Object> params = new HashMap<>();
		// params.put( "groupCode", group );
		// params.put( "paramsCodeList", codes );
		//
		// List<OnlineParams> onlineParams = this.onlineParamsService.queryByPage(
		// params, -1, -1 );
		
		// if(CollectionUtil.isNotEmpty(onlineParams)){
		// result = new HashMap<>();
		// for (OnlineParams onlineParam : onlineParams) {
		// result.put(onlineParam.getParamCode(),onlineParam.getParamValue());
		// }
		// }
		
		Map<String, String> result = new HashMap<>();
		
		for (String code : codes) {
			result.put( code, this.onlineParamsCache.getValue( group, code ) );
		}
		
		return result;
	}
	
	@Override
	public void initCache() {
		List<OnlineParams> list = this.onlineParamsService.queryByPage( null, -1, -1 );
		if (CollectionUtil.isNotEmpty( list )) {
			for (OnlineParams onlineParams : list) {
				// 放入缓存
				this.onlineParamsCache.putValue( onlineParams.getGroupCode(), onlineParams.getParamCode(), onlineParams.getParamValue() );
			}
		}
	}
	
	private void refreshCache(String group, String code, String value) {
		this.onlineParamsCache.putValue( group, code, value );
	}
}
