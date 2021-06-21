package com.xx.netty.client;

import com.xx.netty.Constants;
import com.xx.netty.client.handler.FlowLimitResponseHandler;
import com.xx.netty.client.handler.TokenClientPromiseHolder;
import com.xx.netty.codec.PacketDecoder;
import com.xx.netty.codec.PacketEncoder;
import com.xx.netty.protocol.Packet;
import com.xx.netty.protocol.request.FlowLimitRequestPacket;
import com.xx.netty.protocol.response.FlowLimitResponsePacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.AbstractMap;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private final AtomicInteger idGenerator = new AtomicInteger(0);
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 999_999_999;
    private final AtomicInteger currentState = new AtomicInteger(Constants.CLIENT_STATUS_OFF);
    private final ConcurrentMap<String /* addr */, ChannelWrapper> channelTables = new ConcurrentHashMap<String, ChannelWrapper>();
    private final Lock lockChannelTables = new ReentrantLock();
    private Bootstrap bootstrap = null;
    private Channel channel;

    public void start(){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .option(ChannelOption.TCP_NODELAY, true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        //ch.pipeline().addLast( new IdleStateHandler(0, 0, 5));
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7,4));
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new FlowLimitResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                     }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        if(!currentState.compareAndSet(Constants.CLIENT_STATUS_OFF, Constants.CLIENT_STATUS_PENDING)){
            return;
        }
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 客户端连接成功");
                channel = ((ChannelFuture) future).channel();
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    public Packet sendRequest(Packet packet) throws Exception {
        int id = getCurrentId();
        try {
            packet.setId(id);

            this.channel.writeAndFlush(packet);
            ChannelPromise promise = channel.newPromise();
            TokenClientPromiseHolder.putPromise(packet.getId(), promise);
            if (!promise.await(1000)) {
                throw new RuntimeException("request time out");
            }
            AbstractMap.SimpleEntry<ChannelPromise, Packet> entry = TokenClientPromiseHolder.getEntry(id);
            if (entry == null || entry.getValue() == null) {
                // Should not go through here.
                throw new RuntimeException("request time out");
            }
            return entry.getValue();
        } finally {
            TokenClientPromiseHolder.remove(id);
        }
    }

    private int getCurrentId() {
        int pre, next;
        do {
            pre = idGenerator.get();
            next = pre >= MAX_ID ? MIN_ID : pre + 1;
        } while (!idGenerator.compareAndSet(pre, next));
        return next;
    }



    static class ChannelWrapper {
        private final ChannelFuture channelFuture;

        public ChannelWrapper(ChannelFuture channelFuture) {
            this.channelFuture = channelFuture;
        }

        public boolean isOK() {
            return this.channelFuture.channel() != null && this.channelFuture.channel().isActive();
        }

        public boolean isWritable() {
            return this.channelFuture.channel().isWritable();
        }

        private Channel getChannel() {
            return this.channelFuture.channel();
        }

        public ChannelFuture getChannelFuture() {
            return channelFuture;
        }
    }


    public static void main(String[] args) throws Exception {
        final NettyClient nettyClient = new NettyClient();
        nettyClient.start();
        Thread.sleep(1000);

        for (int i = 0; i < 50; i++) {
            long start = System.currentTimeMillis();
            try {
                final FlowLimitRequestPacket requestPacket = new FlowLimitRequestPacket();
                requestPacket.setResourceKey("/xxx/s/x/s/");
                requestPacket.setQps(10);
                final FlowLimitResponsePacket responsePacket = (FlowLimitResponsePacket)nettyClient.sendRequest(requestPacket);
                System.out.println("res:" + responsePacket + ",cost:" + (System.currentTimeMillis() - start));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("has exception!, cost:"+(System.currentTimeMillis() - start));
            }
        }

    }
}