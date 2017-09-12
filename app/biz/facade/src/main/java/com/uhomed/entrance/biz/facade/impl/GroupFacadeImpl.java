package com.uhomed.entrance.biz.facade.impl;

import com.uhomed.entrance.biz.facade.GroupFacade;
import com.uhomed.entrance.biz.service.GroupService;
import com.uhomed.entrance.core.utils.PageModel;
import com.uhomed.entrance.dal.result.Result;
import com.uhomed.entrance.model.Group;
import com.uhomed.entrance.view.GroupView;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("groupFacade")
public class GroupFacadeImpl implements GroupFacade{


    @Resource
    private GroupService groupService;

    @Override
    public Result<String> createGroup(String code, String name) {

        Result<String> result = new Result<>();

        Map<String,Object> params = new HashMap<>();
        params.put("code",code);

        int count = this.groupService.queryByCount(params);
        if(count > 0){
            result.setMessage("当前code已存在！");
            return result;
        }

        Group group = new Group();
        group.setCode(code);
        group.setName(name);
        count = (int)this.groupService.create(group);
        if(count > 0){
            result.setMessage("添加成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("添加失败！");
        }

        return result;
    }

    @Override
    public Result<String> deleteGroup(String code) {

        Result<String> result = new Result<>();

        Group group = new Group();
        group.setCode(code);

        int flag = this.groupService.delete(group,false);
        if(flag > 0){
            result.setMessage("删除成功！");
            result.setSuccess(true);
            //TODO 删除分组时，将方法和方法参数级联删除
            return result;
        }else {
            result.setMessage("删除失败！");
        }

        return result;
    }

    @Override
    public Result<String> updateGroup(String code, String name) {

        Result<String> result = new Result<>();

        Group group = new Group();
        group.setCode(code);
        group.setName(name);
        int count = this.groupService.update(group);
        if(count > 0){
            result.setMessage("更新成功！");
            result.setSuccess(true);
        }else {
            result.setMessage("更新失败！");
        }

        return result;
    }

    @Override
    public PageModel<GroupView> groupList(String likeCode, String likeName, Integer currPage, Integer pageSize) {
        PageModel<GroupView> page = new PageModel<>();
        Map<String,Object> params = new HashMap<>();
        params.put("likeCode",likeCode);
        params.put("likeName",likeName);
        List<Group> groups = this.groupService.queryByPage(params,currPage,pageSize);
        page.setCount(this.groupService.queryByCount(params));
        if(CollectionUtils.isNotEmpty(groups)){
            List<GroupView> datas = new ArrayList<>();
            for(Group group : groups){
                //TODO 方法数量
                datas.add(new GroupView(group.getCode(),group.getName(),0,group.getCreateTime()));
            }
            page.setDatas(datas);
        }

        return page;
    }
}
