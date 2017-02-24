package net.myscloud.plugin.logging.logback.redis.appender;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import org.redisson.client.codec.Codec;
import org.redisson.client.handler.State;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

import java.io.IOException;

/**
 * FastjsonCodec
 *
 * @author Genesis
 * @since 1.0
 */
class FastjsonCodec implements Codec {

    private final Encoder encoder = new Encoder() {
        @Override
        public byte[] encode(Object in) throws IOException {
            return JSON.toJSONBytes(in);
        }
    };

    private final Decoder decoder = new Decoder() {
        @Override
        public Object decode(ByteBuf buf, State state) throws IOException {
            // TODO: 2017/1/17 暂时不实现Decoder
            return null;
        }
    };

    @Override
    public Decoder<Object> getMapValueDecoder() {
        return decoder;
    }

    @Override
    public Encoder getMapValueEncoder() {
        return encoder;
    }

    @Override
    public Decoder<Object> getMapKeyDecoder() {
        return decoder;
    }

    @Override
    public Encoder getMapKeyEncoder() {
        return encoder;
    }

    @Override
    public Decoder<Object> getValueDecoder() {
        return decoder;
    }

    @Override
    public Encoder getValueEncoder() {
        return encoder;
    }
}
