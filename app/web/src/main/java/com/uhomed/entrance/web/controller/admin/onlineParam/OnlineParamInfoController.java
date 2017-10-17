package com.uhomed.entrance.web.controller.admin.onlineParam;

import com.uhomed.entrance.biz.facade.OnlineParamsFacade;
import com.uhomed.entrance.model.OnlineParams;
import com.uhomed.entrance.web.controller.BaseController;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Controller
@Scope("prototype")
public class OnlineParamInfoController extends BaseController {
	
	@Resource
	private OnlineParamsFacade onlineParamsFacade;
	
	@RequestMapping(value = "/admin/onlineParam/info", method = { RequestMethod.GET })
	public ModelAndView info(String group, String code) {
		ModelAndView result = new ModelAndView();
		
		if (StrUtil.isEmpty( group ) || StrUtil.isEmpty( code )) {
			super.setFailMessage( result, "必填的参数不能为空！" );
			return result;
		}
		
		OnlineParams info = this.onlineParamsFacade.queryOnlineParamsByCode( group, code );
		
		super.setSuccessful( result, "查询成功！" );
		result.addObject( "info", info );
		
		return result;
	}
}
