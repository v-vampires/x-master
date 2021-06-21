package com.xx.netty.protocol;

import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    private int id;


    public abstract Byte getCommand();
}