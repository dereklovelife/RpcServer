package rpcServer.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpcServer.registry.ServiceRegistry;
import rpcServer.service.StringService;
import rpcServer.service.model.StringResultModel;

/**
 * Created by Administrator on 2018/8/4.
 */
@Component
public class StringServiceImpl implements StringService, InitializingBean{

    @Autowired
    private ServiceRegistry registry;

    public void afterPropertiesSet() throws Exception {
        // 注册之后完成注册
        registry.registerService(StringService.class.getSimpleName(), this);
        registry.registerClassByName(StringService.class.getSimpleName(), StringService.class);
        System.out.println("StringService: 注册完成");
    }
    public String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public StringResultModel buildModel(String s1, String s2) {
        StringResultModel model = new StringResultModel();
        model.setS1(s1);
        model.setS2(s2);
        return model;
    }
}
