package com.xx.netty.client.handler;

import com.xx.netty.protocol.Packet;
import io.netty.channel.ChannelPromise;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TokenClientPromiseHolder {

    private static final Map<Integer, AbstractMap.SimpleEntry<ChannelPromise, Packet>> PROMISE_MAP = new ConcurrentHashMap<>();

    public static void putPromise(int xid, ChannelPromise promise) {
        PROMISE_MAP.put(xid, new AbstractMap.SimpleEntry<ChannelPromise, Packet>(promise, null));
    }

    public static AbstractMap.SimpleEntry<ChannelPromise, Packet> getEntry(int xid) {
        return PROMISE_MAP.get(xid);
    }

    public static void remove(int xid) {
        PROMISE_MAP.remove(xid);
    }

    public static <T> boolean completePromise(int xid, Packet response) {
        if (!PROMISE_MAP.containsKey(xid)) {
            return false;
        }
        AbstractMap.SimpleEntry<ChannelPromise, Packet> entry = PROMISE_MAP.get(xid);
        if (entry != null) {
            ChannelPromise promise = entry.getKey();
            if (promise.isDone() || promise.isCancelled()) {
                return false;
            }
            entry.setValue(response);
            promise.setSuccess();
            return true;
        }
        return false;
    }

    private TokenClientPromiseHolder() {}
}