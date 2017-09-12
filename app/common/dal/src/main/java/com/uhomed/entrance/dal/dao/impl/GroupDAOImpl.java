package com.uhomed.entrance.dal.dao.impl;

import com.uhomed.entrance.core.dao.impl.BaseDAOImpl;
import com.uhomed.entrance.dal.dao.GroupDAO;
import com.uhomed.entrance.model.Group;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Repository("groupDAO")
public class GroupDAOImpl extends BaseDAOImpl<Group> implements GroupDAO{


    public GroupDAOImpl() {
        super(Group.class);
    }
}
