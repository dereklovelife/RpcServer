package rpcServer.common.encode;

import rpcServer.common.model.RpcRequest;
import rpcServer.common.model.RpcResult;

/**
 * Created by Administrator on 2018/8/4.
 */
public interface EncodeExecutor {

    byte[] buildRequest(RpcRequest request);

    byte[] buildResponse(RpcResult result);

    RpcRequest toRequest(byte[] bytes);

    RpcResult toReuslt(byte[] bytes);
}
