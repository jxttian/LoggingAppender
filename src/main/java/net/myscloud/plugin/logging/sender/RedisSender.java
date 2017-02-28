package net.myscloud.plugin.logging.sender;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.LoggerEvent;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;

/**
 * Redis消息发送者
 */
@Setter
@Getter
@Builder
public class RedisSender implements MessageSender {

    private RedissonClient redis;

    private RQueue<LoggerEvent> queue;

    private String topic;

    private Codec codec = new FastjsonCodec();

    @Override
    public boolean send(LoggerEvent event) {
        return queue.offer(event);
    }

    @Override
    public boolean sendAsync(LoggerEvent event) {
        queue.offerAsync(event);
        return true;
    }

    @Override
    public boolean send(String topic, LoggerEvent event) {
        return redis.getQueue(topic, new FastjsonCodec()).offer(event);
    }

    @Override
    public boolean sendAsync(String topic, LoggerEvent event) {
        redis.getQueue(topic, new FastjsonCodec()).offerAsync(event);
        return true;
    }

    @Override
    public void init() {
        // init
        queue = redis.getQueue(topic, new FastjsonCodec());
    }

    @Override
    public void destroy() {
        if (redis == null || redis.isShutdown() || redis.isShuttingDown()) {
            return;
        }
        redis.shutdown();
    }
}
