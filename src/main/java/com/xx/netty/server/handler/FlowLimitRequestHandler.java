package com.xx.netty.server.handler;

import com.xx.netty.protocol.request.FlowLimitRequestPacket;
import com.xx.netty.protocol.response.FlowLimitResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class FlowLimitRequestHandler extends SimpleChannelInboundHandler<FlowLimitRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FlowLimitRequestPacket requestPacket) throws Exception {

        boolean pass = process(requestPacket);
        final FlowLimitResponsePacket responsePacket = new FlowLimitResponsePacket();
        responsePacket.setId(requestPacket.getId());
        responsePacket.setVersion(requestPacket.getVersion());
        responsePacket.setPass(pass);
        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean process(FlowLimitRequestPacket requestPacket) {
        System.out.println("进行redis限流判断, requestPacket:" + requestPacket);
        return true;
    }
}
