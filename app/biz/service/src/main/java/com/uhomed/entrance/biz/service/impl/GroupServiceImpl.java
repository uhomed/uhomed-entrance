package com.uhomed.entrance.biz.service.impl;

import com.uhomed.entrance.biz.service.GroupService;
import com.uhomed.entrance.core.service.impl.BaseServiceImpl;
import com.uhomed.entrance.dal.dao.GroupDAO;
import com.uhomed.entrance.model.Group;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Group> implements GroupService{


    @Resource
    private GroupDAO groupDAO;


    @PostConstruct
    public void init(){
        super.setBaseDao(groupDAO);
    }


}
