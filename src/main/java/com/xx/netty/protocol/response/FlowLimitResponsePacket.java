package com.xx.netty.protocol.response;

import com.xx.netty.protocol.Packet;
import com.xx.netty.protocol.command.Command;
import lombok.Data;

import java.io.Serializable;

@Data
public class FlowLimitResponsePacket extends Packet implements Serializable {

    private boolean pass;

    @Override
    public Byte getCommand() {
        return Command.FLOW_LIMIT_RESPONSE;
    }
}
