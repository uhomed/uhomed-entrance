package com.uhomed.entrance.web.controller.admin.method;

import com.uhomed.entrance.biz.facade.MethodFacade;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class DeleteMethodController extends BaseController{

    @Resource
    private MethodFacade methodFacade;


    @RequestMapping(value = "/admin/method/delete", method = { RequestMethod.POST })
    public ModelAndView delete(Integer id) {
        ModelAndView result = new ModelAndView();
        Result<String> r = this.methodFacade.deleteMethod(id);
        super.setResultModel(result,r);
        return result;
    }
}
