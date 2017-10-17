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
public class DeleteOnlineParamController extends BaseController {
	
	@Resource
	private OnlineParamsFacade onlineParamsFacade;
	
	@RequestMapping(value = "/admin/onlineParam/delete", method = { RequestMethod.POST })
	public ModelAndView delete(String group, String code) {
		ModelAndView result = new ModelAndView();
		
		if (StrUtil.isEmpty( group ) || StrUtil.isEmpty( code )) {
			super.setFailMessage( result, "必填的参数不能为空！" );
			return result;
		}
		
		Result<String> resultObj = this.onlineParamsFacade.deleteOnlineParams( group, code );
		if (resultObj.isSuccess()) {
			super.setSuccessful( result, resultObj.getMessage() );
		} else {
			super.setFailMessage( result, resultObj.getMessage() );
		}
		
		return result;
	}
}
