package com.xx.netty.protocol.request;

import com.xx.netty.protocol.Packet;
import com.xx.netty.protocol.command.Command;
import lombok.Data;

import java.io.Serializable;

@Data
public class FlowLimitRequestPacket extends Packet implements Serializable {

    private String resourceKey;

    private int qps;

    @Override
    public Byte getCommand() {
        return Command.FLOW_LIMIT_REQUEST;
    }
}
