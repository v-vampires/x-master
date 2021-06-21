package com.xx.netty.client.handler;

import com.xx.netty.protocol.response.FlowLimitResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FlowLimitResponseHandler extends SimpleChannelInboundHandler<FlowLimitResponsePacket> {



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FlowLimitResponsePacket response) throws Exception {
        System.out.println("flow limit response handler:" + response);
        TokenClientPromiseHolder.completePromise(response.getId(), response);
    }

}
