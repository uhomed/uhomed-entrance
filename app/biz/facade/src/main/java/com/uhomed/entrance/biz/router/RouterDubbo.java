package com.uhomed.entrance.biz.router;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.uhomed.entrance.biz.cache.dto.MethodCacheDTO;
import com.uhomed.entrance.biz.cache.dto.MethodDubboDTO;
import com.uhomed.entrance.core.utils.common.LoadConfig;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @author
 * @version $$Id: , v 0.1 Exp $$
 */
public class RouterDubbo implements Router {
	
	private static String ROUTER_URL = "condition://0.0.0.0/{0}?category=routers&dynamic=false&enabled=true&force=true&name=ss&priority=12&router=condition&rule==> provider.host = \" {1}\"&runtime=true\"";
	
	@Override
	public void router(MethodCacheDTO methodDTO, String router) {
		Registry registry = null;
		URL url = null;
		if (StrUtil.isNotEmpty( router )) {
			// 是否设置动态路由
			MethodDubboDTO dubbo = (MethodDubboDTO) methodDTO.getMethodInfo();
			RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader( RegistryFactory.class )
					.getAdaptiveExtension();
			registry = registryFactory.getRegistry(
					URL.valueOf( "zookeeper://" + LoadConfig.getInstance().getValue( "zookeeper.ip.config" ) ) );
			// url = URL.valueOf( "condition://0.0.0.0/" + dubbo.getClassPath()
			// +
			// "?category=routers&dynamic=false&enabled=true&force=true&name=ss&priority=12&router=condition&rule==>
			// provider.host = "
			// + router + "&runtime=true" );
			url = URL.valueOf( MessageFormat.format( ROUTER_URL, dubbo.getClassPath(), router ) );
			registry.register( url );
			try {
				// 休眠100毫秒，因为zookeeper设置有需要时间
				Thread.sleep( 100l );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
