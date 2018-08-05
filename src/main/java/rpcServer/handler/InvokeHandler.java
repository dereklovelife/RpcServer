package rpcServer.handler;

import rpcServer.common.model.RpcRequest;
import rpcServer.common.model.RpcResult;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface InvokeHandler {

    RpcResult invoke(RpcRequest request);
}
