package rpcServer.registry.impl;

import org.springframework.stereotype.Component;
import rpcServer.registry.ServiceRegistry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class ServiceRegistryImpl implements ServiceRegistry{

    //TODO: 可以增加服务发现与注册

    // service registry
    private ConcurrentHashMap<String, Object> serviceRegistryMap = new ConcurrentHashMap<String, Object>(5);

    private ConcurrentHashMap<String, Class> classRegistryMap = new ConcurrentHashMap<String, Class>(5);

    public Object getProxyByInterface(String interfaceType) {
        return serviceRegistryMap.get(interfaceType);
    }

    public void registerService(String interfaceType, Object proxy) {
        //todo: 增加服务注册
        serviceRegistryMap.put(interfaceType, proxy);
    }

    public void unregisterService(String interfaceType) {
        serviceRegistryMap.remove(interfaceType);
    }

    public Class getClassByName(String name) {
        return classRegistryMap.get(name);
    }

    public void registerClassByName(String name, Class c) {
        classRegistryMap.put(name, c);
    }
}
