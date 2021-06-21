package com.xx.netty.serialize.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.xx.netty.serialize.Serializer;
import com.xx.netty.serialize.SerializerAlogrithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hessian2Serializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.HESSIAN2;
    }

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output ho = new Hessian2Output(os);
        byte[] cc = null;
        try {
            if (obj == null) throw new NullPointerException();
            ho.writeObject(obj);
            ho.flushBuffer();
            cc = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ho.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cc;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        try {
            if (bytes == null) throw new NullPointerException();
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            Hessian2Input hi = new Hessian2Input(is);
            return (T) hi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
