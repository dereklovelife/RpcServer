package rpcServer.registry;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface ServiceRegistry {


    Object getProxyByInterface(String interfaceType);

    void registerService(String interfaceType, Object object);

    void unregisterService(String interfaceType);

    Class getClassByName(String name);

    void registerClassByName(String name, Class c);

}
