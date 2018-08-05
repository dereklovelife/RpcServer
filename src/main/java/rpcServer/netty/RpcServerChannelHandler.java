package rpcServer.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rpcServer.common.encode.EncodeExecutor;
import rpcServer.handler.InvokeHandler;
import rpcServer.common.model.RpcRequest;
import rpcServer.common.model.RpcResult;

/**
 * Created by Administrator on 2018/8/4.
 */

@Component
@ChannelHandler.Sharable
public class RpcServerChannelHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Autowired
    private EncodeExecutor encodeExecutor;

    @Autowired
    private InvokeHandler invokeHandler;

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("receive request from" + channelHandlerContext.channel().remoteAddress());
        byte[] request = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(request);
        RpcRequest rpcRequest = encodeExecutor.toRequest(request);
        RpcResult rpcResult = invokeHandler.invoke(rpcRequest);
        byte[] response = encodeExecutor.buildResponse(rpcResult);
        ChannelFuture cf = channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(response));
        cf.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.channel().closeFuture();
    }
}
