package com.uhomed.entrance.web.controller.admin.group;

import com.uhomed.entrance.biz.facade.GroupFacade;
import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.view.GroupView;
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
public class GroupListController extends BaseController{

    @Resource
    private GroupFacade groupFacade;


    @RequestMapping(value = "/admin/group/list", method = { RequestMethod.GET })
    public ModelAndView list(String likeCode,String likeName,Integer currPage,Integer pageSize) {
        ModelAndView result = new ModelAndView();

        if(currPage == null || pageSize == null){
            super.setFailMessage(result,"分页数据不能为空！");
            return result;
        }

        PageModel<GroupView> page = this.groupFacade.groupList(likeCode, likeName, currPage, pageSize);

        result.addObject("data",page.getDatas());
        result.addObject("count",page.getCount());

        return result;
    }

}
