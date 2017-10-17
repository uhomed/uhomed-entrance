package com.uhomed.entrance.biz.service.impl;

import com.uhomed.entrance.biz.service.OnlineParamsService;
import com.uhomed.entrance.core.service.impl.BaseServiceImpl;
import com.uhomed.entrance.dal.dao.OnlineParamsDAO;
import com.uhomed.entrance.model.OnlineParams;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
@Service("onlineParamsService")
public class OnlineParamsServiceImpl extends BaseServiceImpl<OnlineParams> implements OnlineParamsService {
	
	@Resource
	private OnlineParamsDAO onlineParamsDAO;
	
	@PostConstruct
	public void init() {
		super.setBaseDao( onlineParamsDAO );
	}
}
