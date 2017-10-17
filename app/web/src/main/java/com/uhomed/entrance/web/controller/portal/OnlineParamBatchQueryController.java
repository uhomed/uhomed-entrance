package com.uhomed.entrance.web.controller.portal;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uhomed.entrance.biz.facade.OnlineParamsFacade;
import com.uhomed.entrance.web.controller.BaseController;
import com.xiaoleilu.hutool.util.StrUtil;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Controller
@Scope("prototype")
public class OnlineParamBatchQueryController extends BaseController {
	
	@Resource
	private OnlineParamsFacade onlineParamsFacade;
	
	@RequestMapping(value = "/param/query", method = { RequestMethod.POST })
	public ModelAndView info(String group, String code) {
		ModelAndView result = new ModelAndView();
		
		if (StrUtil.isEmpty( group ) || StrUtil.isEmpty( code )) {
			super.setFailMessage( result, "必填的参数不能为空！" );
			return result;
		}
		
		Map<String, String> paramsMap = this.onlineParamsFacade.queryOnlineParamsByPage( group, code );
		super.setSuccessful( result, "查询成功！" );
		result.addObject( "data", paramsMap );
		
		return result;
	}
	
}
