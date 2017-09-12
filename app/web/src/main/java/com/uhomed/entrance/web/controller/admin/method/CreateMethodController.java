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
public class CreateMethodController extends BaseController{

    @Resource
    private MethodFacade methodFacade;


    @RequestMapping(value = "/admin/method/createDubbo", method = { RequestMethod.POST })
    public ModelAndView create(String groupCode,String apiMethodCode,String apiMethodName,String apiMethodVersion,String status,String verifiSso,String mode,String methodDesc,String classPath,String methodName) {
        ModelAndView result = new ModelAndView();
        Result<Integer> r = this.methodFacade.createMethodDubbo(groupCode,apiMethodCode, apiMethodName, apiMethodVersion, status, verifiSso, mode, methodDesc, classPath, methodName);
        super.setResultModel(result,r);
        return result;
    }
}
