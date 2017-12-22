package com.uhomed.entrance.biz.init;

import javax.annotation.Resource;

import com.uhomed.entrance.biz.facade.OnlineParamsFacade;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.uhomed.entrance.biz.facade.MethodFacade;

/**
 * @author
 * @version $$Id: , v 0.1    Exp $$
 */
@Component
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private MethodFacade methodFacade;

    @Resource
    private OnlineParamsFacade onlineParamsFacade;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        System.out.println("=====================   开始启动自定义服务   =====================");
        //初始化方法
        this.methodFacade.initCache();
        //初始化在线参数
        this.onlineParamsFacade.initCache();

		System.out.println("=====================   启动自定义服务完成！  =====================");
    }

}
