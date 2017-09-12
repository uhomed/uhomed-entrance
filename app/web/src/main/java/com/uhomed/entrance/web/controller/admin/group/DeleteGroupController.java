package com.uhomed.entrance.web.controller.admin.group;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.uhomed.entrance.biz.facade.GroupFacade;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.web.controller.BaseController;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Controller
@Scope("prototype")
public class DeleteGroupController extends BaseController {

    @Resource
    private GroupFacade groupFacade;

    @RequestMapping(value = "/admin/group/delete", method = { RequestMethod.POST })
    public ModelAndView delete(String code) {
        ModelAndView result = new ModelAndView();
        Result<String> r = this.groupFacade.deleteGroup(code);
        super.setResultModel(result,r);
        return result;
    }
}
