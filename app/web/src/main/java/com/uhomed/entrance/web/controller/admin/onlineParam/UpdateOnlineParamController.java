package com.uhomed.entrance.web.controller.admin.onlineParam;

import com.uhomed.entrance.biz.facade.OnlineParamsFacade;
import com.uhomed.entrance.dal.result.Result;
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
public class UpdateOnlineParamController extends BaseController {
	
	@Resource
	private OnlineParamsFacade onlineParamsFacade;
	
	@RequestMapping(value = "/admin/onlineParam/update", method = { RequestMethod.POST })
	public ModelAndView update(String group, String code, String value, String remark) {
		ModelAndView result = new ModelAndView();
		
		if (StrUtil.isEmpty( group ) || StrUtil.isEmpty( code ) || StrUtil.isEmpty( value )) {
			super.setFailMessage( result, "必填的参数不能为空！" );
			return result;
		}
		
		Result<String> resultObj = this.onlineParamsFacade.updateOnlineParams( group, code, value, remark );
		if (resultObj.isSuccess()) {
			super.setSuccessful( result, resultObj.getMessage() );
		} else {
			super.setFailMessage( result, resultObj.getMessage() );
		}
		return result;
	}
}
