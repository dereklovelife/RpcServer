package rpcServer.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpcServer.registry.ServiceRegistry;
import rpcServer.service.HashService;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class HashServiceImpl implements HashService, InitializingBean {

    @Autowired
    private ServiceRegistry registry;

    public int getHashCode(String s) {
        return s.hashCode();
    }

    public void afterPropertiesSet() throws Exception {
        // 注册之后完成注册
        registry.registerService(HashService.class.getSimpleName(), this);
        registry.registerClassByName(HashService.class.getSimpleName(), HashService.class);
        System.out.println("HashService: 注册完成");
    }
}
