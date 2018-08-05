package rpcServer.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpcServer.common.model.RpcRequest;
import rpcServer.common.model.RpcResult;
import rpcServer.registry.ServiceRegistry;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
public class InvokeHanlderImpl implements InvokeHandler{
    @Autowired
    private ServiceRegistry serviceRegistry;

    public RpcResult invoke(RpcRequest rpcRequest) {
        RpcResult result = new RpcResult();
        if(rpcRequest == null) {
            result.setRetval("");
            result.setSuccess("F");
            result.setErrorCode("解析错误");
            return result;
        }

        String interfaceType = rpcRequest.getInterfaceType();
        Object proxy = serviceRegistry.getProxyByInterface(interfaceType);
        if(proxy == null) {
            result.setRetval(null);
            result.setSuccess("F");
            result.setErrorCode("找不到服务:" + interfaceType);
        }
        Class interfaceClass = serviceRegistry.getClassByName(interfaceType);

        String methodName = rpcRequest.getMethodName();
        Method method = null;
        for(Method m: interfaceClass.getMethods()){
            if(m.getName().equals(methodName)){
                method = m;
                break;
            }
        }
        try{
            Object invokeResult = method.invoke(proxy, rpcRequest.getArgs());
            result.setRetval(invokeResult);
            result.setSuccess("T");
        } catch (Exception e) {
            result.setRetval(null);
            result.setSuccess("F");
            result.setErrorCode("exception:" + e.getMessage());
        }
        return result;
    }
}
