package com.uhomed.entrance.web.controller.admin.onlineParam;

import com.uhomed.entrance.biz.facade.OnlineParamsFacade;
import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.model.OnlineParams;
import com.uhomed.entrance.web.controller.BaseController;
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
public class OnlineParamListController extends BaseController {
	
	@Resource
	private OnlineParamsFacade onlineParamsFacade;
	
	@RequestMapping(value = "/admin/onlineParam/list", method = { RequestMethod.GET })
	public ModelAndView list(String group, String searchKey, Integer currPage, Integer pageSize) {
		ModelAndView result = new ModelAndView();
		
		if (currPage == null || pageSize == null) {
			super.setFailMessage( result, "必填参数不能为空！" );
			return result;
		}
		
		PageModel<OnlineParams> page = this.onlineParamsFacade.queryOnlineParamsByPage( group, searchKey, currPage, pageSize );
		
		result.addObject( "data", page.getDatas() );
		result.addObject( "count", page.getCount() );
		
		return result;
	}
	
}
