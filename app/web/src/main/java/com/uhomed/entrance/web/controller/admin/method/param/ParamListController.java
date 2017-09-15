package com.uhomed.entrance.web.controller.admin.method.param;

import com.uhomed.entrance.biz.facade.MethodFacade;
import com.uhomed.entrance.model.MethodInfo;
import com.uhomed.entrance.view.MethodParamView;
import com.uhomed.entrance.web.controller.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class ParamListController extends BaseController{

    @Resource
    private MethodFacade methodFacade;


    @RequestMapping(value = "/admin/method/param/list", method = { RequestMethod.GET })
    public ModelAndView list(Integer methodId) {
        ModelAndView result = new ModelAndView();

        List<MethodParamView> datas = this.methodFacade.queryParams(methodId);

        MethodInfo info = this.methodFacade.queryById(methodId);
        result.addObject("info",info);

        result.addObject("data",datas);
        return result;
    }
}
