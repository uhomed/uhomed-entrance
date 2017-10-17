package com.uhomed.entrance.biz.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.uhomed.entrance.biz.cache.local.MethodCache;
import com.uhomed.entrance.biz.cache.dto.MethodDTO;
import com.uhomed.entrance.biz.cache.dto.MethodDubboDTO;
import com.uhomed.entrance.biz.cache.dto.MethodParamCacheDTO;
import com.uhomed.entrance.biz.context.MethodTypeContext;
import com.uhomed.entrance.biz.context.ParamClazzContext;
import com.uhomed.entrance.biz.facade.MethodFacade;
import com.uhomed.entrance.biz.service.MethodDubboService;
import com.uhomed.entrance.biz.service.MethodInfoService;
import com.uhomed.entrance.biz.service.MethodParamService;
import com.uhomed.entrance.core.utils.BeanUtil;
import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.model.MethodDubbo;
import com.uhomed.entrance.model.MethodInfo;
import com.uhomed.entrance.model.MethodParam;
import com.uhomed.entrance.view.MethodInfoView;
import com.uhomed.entrance.view.MethodParamView;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * The type MethodDTO facade.
 *
 * @author
 * @version $$Id : , v 0.1 Exp $$
 */
@Service("methodFacade")
public class MethodFacadeImpl implements MethodFacade {
	
	@Resource
	private MethodInfoService	methodInfoService;
	
	@Resource
	private MethodDubboService	methodDubboService;
	
	@Resource
	private MethodParamService	methodParamService;
	
	@Resource
	private MethodCache			methodCache;
	
	@Override
	public Result<Integer> createMethod(String groupCode, String apiMethodCode, String apiMethodName, String apiMethodVersion, String status,
			String verifiSso, String mode, String methodDesc, String type) {
		Result<Integer> result = new Result<>();
		
		MethodInfo methodInfo = new MethodInfo();
		methodInfo.setApiMethodCode( apiMethodCode );
		methodInfo.setApiMethodName( apiMethodName );
		methodInfo.setApiMethodVersion( apiMethodVersion );
		methodInfo.setMethodDesc( methodDesc );
		methodInfo.setStatus( status );
		methodInfo.setVerifiSso( verifiSso );
		methodInfo.setMode( mode );
		methodInfo.setType( type );
		methodInfo.setGroupCode( groupCode );
		
		int count = (int) this.methodInfoService.create( methodInfo );
		if (count > 0) {
			result.setData( methodInfo.getId() );
			result.setSuccess( true );
			result.setMessage( "添加方法成功！" );
		} else {
			result.setMessage( "添加方法失败！" );
		}
		
		return result;
	}
	
	@Override
	public Result<String> deleteMethod(Integer id) {
		Result<String> result = new Result<>();
		MethodInfo info = new MethodInfo();
		info.setId( id );
		int count = this.methodInfoService.delete( info, false );
		if (count > 0) {
			result.setMessage( "删除成功！" );
			result.setSuccess( true );
		} else {
			result.setMessage( "删除失败！" );
		}
		return result;
	}
	
	@Override
	public Result<String> updateMethod(Integer id, String apiMethodCode, String apiMethodName, String apiMethodVersion, String status,
			String verifiSso, String mode, String methodDesc, String type, String classPath, String methodName, List<MethodParam> paramList) {
		Result<String> result = new Result<>();
		
		MethodInfo methodInfo = new MethodInfo();
		methodInfo.setApiMethodCode( apiMethodCode );
		methodInfo.setApiMethodName( apiMethodName );
		methodInfo.setApiMethodVersion( apiMethodVersion );
		methodInfo.setMethodDesc( methodDesc );
		methodInfo.setStatus( status );
		methodInfo.setVerifiSso( verifiSso );
		methodInfo.setMode( mode );
		methodInfo.setType( type );
		methodInfo.setId( id );
		
		int count = (int) this.methodInfoService.update( methodInfo );
		if (count > 0) {
			result.setSuccess( true );
			result.setMessage( "更新方法成功！" );
			
			// TODO 如果只单是更新状态，则不更新dubbo
			if (StrUtil.isNotEmpty( classPath )) {
				MethodDubbo vo = new MethodDubbo();
				vo.setMethodId( id );
				vo.setClassPath( classPath );
				vo.setMethodName( methodName );
				this.methodDubboService.update( vo );
				
				MethodParam del = new MethodParam();
				del.setMethodId( id );
				this.methodParamService.delete( del, false );
				
				// 更新参数
				if (CollectionUtil.isNotEmpty( paramList )) {
					List<MethodParam> creates = new ArrayList<>();
					for (int i = 0; i < paramList.size(); i++) {
						MethodParam param = paramList.get( i );
						param.setParamIndex( i + 1 );
						param.setMethodId( id );
						creates.add( param );
					}
					if (CollectionUtil.isNotEmpty( creates )) {
						this.methodParamService.batchCreate( creates );
					}
				}
			}
			
			this.refreshMethodCache( id );
		} else {
			result.setMessage( "更新方法失败！" );
		}
		
		return result;
	}
	
	@Override
	public PageModel<MethodInfoView> methodList(String groupCode, String search, String likeApiMethodName, String status, Integer currPage,
			Integer pageSize) {
		
		PageModel<MethodInfoView> page = new PageModel<>();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put( "groupCode", groupCode );
		params.put( "search", search );
		params.put( "likeApiMethodName", likeApiMethodName );
		params.put( "status", status );
		
		List<MethodInfo> datas = this.methodInfoService.queryByPage( params, currPage, pageSize );
		page.setCount( this.methodInfoService.queryByCount( params ) );
		
		if (CollectionUtils.isNotEmpty( datas )) {
			List<MethodInfoView> infos = new ArrayList<>();
			for (MethodInfo data : datas) {
				String statusCH = StringUtils.equals( data.getStatus(), "Y" ) ? "上线" : "下线";
				String verifiSso = StringUtils.equals( data.getVerifiSso(), "Y" ) ? "是" : "否";
				params.clear();
				params.put( "methodId", data.getId() );
				infos.add( new MethodInfoView( data.getId(), data.getApiMethodCode(), data.getApiMethodName(), data.getApiMethodVersion(),
						data.getStatus(), statusCH, data.getVerifiSso(), verifiSso, data.getMode(), data.getType(), data.getCreateTime(),
						this.methodParamService.queryByCount( params ) ) );
			}
			page.setDatas( infos );
		}
		return page;
	}
	
	@Override
	public Result<Integer> createMethodDubbo(String groupCode, String apiMethodCode, String apiMethodName, String apiMethodVersion, String status,
			String verifiSso, String mode, String methodDesc, String classPath, String methodName, List<MethodParam> paramList) {
		Result<Integer> result = new Result<>();
		Map<String, Object> params = new HashMap<>();
		params.put( "groupCode", groupCode );
		params.put( "apiMethodCode", apiMethodCode );
		params.put( "apiMethodVersion", apiMethodVersion );
		int count = this.methodInfoService.queryByCount( params );
		if (count > 0) {
			result.setMessage( "该code和版本号已存在！" );
			return result;
		}
		
		result = this.createMethod( groupCode, apiMethodCode, apiMethodName, apiMethodVersion, status, verifiSso, mode, methodDesc,
				MethodTypeContext.DUBBO.toString() );
		
		if (result.isSuccess()) {
			// 添加dubbo属性
			MethodDubbo dubbo = new MethodDubbo();
			dubbo.setMethodId( result.getData() );
			dubbo.setClassPath( classPath );
			dubbo.setMethodName( methodName );
			this.methodDubboService.create( dubbo );
			// 添加参数
			if (CollectionUtil.isNotEmpty( paramList )) {
				int i = 1;
				for (MethodParam methodParam : paramList) {
					methodParam.setMethodId( result.getData() );
					methodParam.setParamIndex( i );
					i++;
				}
				this.methodParamService.batchCreate( paramList );
			}
			
			// 添加到缓存
			this.refreshMethodCache( result.getData() );
		}
		return result;
	}
	
	@Override
	public Result<String> updateMethodDubbo(Integer id, String apiMethodCode, String apiMethodName, String apiMethodVersion, String status,
			String verifiSso, String mode, String methodDesc, String classPath, String methodName) {
		
		Result<String> result = this.updateMethodDubbo( id, apiMethodCode, apiMethodName, apiMethodVersion, status, verifiSso, mode, methodDesc,
				classPath, methodName );
		
		if (result.isSuccess()) {
			// 添加dubbo属性
			MethodDubbo dubbo = new MethodDubbo();
			dubbo.setMethodId( id );
			dubbo.setClassPath( classPath );
			dubbo.setMethodName( methodName );
			if (dubbo.getClassPath() != null || dubbo.getMethodName() != null) {
				this.methodDubboService.update( dubbo );
			}
			// 添加到缓存
			this.refreshMethodCache( id );
			
		}
		
		return result;
	}
	
	@Override
	public Result<String> batchUpdateMethodParam(List<MethodParam> params) {
		
		Result<String> result = new Result<>();
		
		List<MethodParam> creates = new ArrayList<>();
		List<MethodParam> updates = new ArrayList<>();
		
		for (MethodParam param : params) {
			if (param.getId() == null) {
				creates.add( param );
			} else {
				updates.add( param );
			}
		}
		
		if (CollectionUtils.isNotEmpty( creates )) {
			this.methodParamService.batchCreate( creates );
		} else {
			this.methodParamService.batchUpdate( updates );
		}
		
		params.clear();
		params.addAll( creates );
		params.addAll( updates );
		this.methodParam2Cache( params );
		result.setMessage( "更新成功！" );
		result.setSuccess( true );
		return result;
	}

	public enum SimpleEnum{
		SIMPLE_ENUM;
	}
	
	/**
	 * param参数转换
	 * 
	 * @param params
	 * @return
	 */
	private List<MethodParamCacheDTO> methodParam2Cache(List<MethodParam> params) {
		List<MethodParamCacheDTO> result = new ArrayList<>();
		if (CollectionUtils.isEmpty( params )) {
			return null;
		}
		for (MethodParam param : params) {
			Object o = null;
			String clazzStr = ParamClazzContext.getClazz( param.getParamType() );
			if (Float.class.getName().equalsIgnoreCase( clazzStr )) {
				o = new Float( 0 );
			} else if (Double.class.getName().equalsIgnoreCase( clazzStr )) {
				o = new Double( 0 );
			} else if (Integer.class.getName().equalsIgnoreCase( clazzStr )) {
				o = new Integer( 0 );
			} else if (Long.class.getName().equalsIgnoreCase( clazzStr )) {
				o = new Long( 0 );
			} else if (Boolean.class.getName().equalsIgnoreCase( clazzStr )) {
				o = new Boolean( false );
			} else if (List.class.getName().equalsIgnoreCase( clazzStr )) {
				o = new ArrayList<>();
			} else if (Enum.class.getName().equalsIgnoreCase( clazzStr )) {
				//如果选择的是枚举类型
				o = SimpleEnum.SIMPLE_ENUM;
			} else {
				try {
					o = Class.forName( clazzStr ).newInstance();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
			result.add( new MethodParamCacheDTO( param.getParamCode(), o, param.getLength(), StringUtils.equals( param.getParamRequire(), "Y" ),
					param.getDefaultValue(), clazzStr, param.getParamName(), param.getMinLength() ) );
		}
		return result;
	}
	
	private void refreshMethodCache(Integer methodId) {
		MethodInfo method = this.methodInfoService.findById( methodId );
		this.refreshMethodCache( method );
	}
	
	private void refreshMethodCache(MethodInfo method) {
		Map<String, Object> params = new HashMap<>();
		params.put( "methodId", method.getId() );
		
		if (StrUtil.equals( method.getType(), MethodTypeContext.DUBBO.toString() )) {
			MethodDubbo dubbo = this.methodDubboService.findById( method.getId() );
			
			MethodDTO m = new MethodDubboDTO( dubbo.getClassPath(), dubbo.getMethodName() );
			this.methodCache.putMethod( method.getId(), method.getApiMethodCode(), method.getApiMethodVersion(), method.getStatus(),
					method.getVerifiSso(), MethodTypeContext.DUBBO, method.getMode(),
					this.methodParam2Cache( this.methodParamService.queryByPage( params, -1, -1 ) ), m );
		}
		
	}
	
	@Override
	public Result<String> deleteMethodParam(Integer id) {
		Result<String> result = new Result<>();
		MethodParam param = this.methodParamService.findById( id );
		if (param == null) {
			result.setMessage( "该参数不存在！" );
			return result;
		}
		
		param.setId( id );
		
		int flag = this.methodParamService.delete( param, false );
		
		if (flag > 0) {
			result.setSuccess( true );
			result.setMessage( "删除成功！" );
			this.refreshMethodCache( param.getMethodId() );
		} else {
			result.setMessage( "删除失败！" );
		}
		return result;
	}
	
	@Override
	public MethodInfo queryById(Integer id) {
		return this.methodInfoService.findById( id );
	}
	
	@Override
	public void initCache() {
		List<MethodInfo> datas = this.methodInfoService.queryByPage( null, -1, -1 );
		for (MethodInfo data : datas) {
			this.refreshMethodCache( data );
		}
	}
	
	@Override
	public MethodDubbo queryMethodDubboById(Integer id) {
		return this.methodDubboService.findById( id );
	}
	
	@Override
	public Result<Integer> updateParams(List<MethodParam> params) {
		return null;
	}
	
	@Override
	public List<MethodParamView> queryParams(Integer methodId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put( "methodId", methodId );
		List<MethodParam> datas = this.methodParamService.queryByPage( params, -1, -1 );
		
		return BeanUtil.convertList( datas, MethodParamView.class );
	}
}
