package net.myscloud.plugin.logging.sender;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.JSONEvent;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;

/**
 * Created by genesis on 2017/2/24.
 */
@Setter
@Getter
@Builder
public class RedisSender implements MessageSender {

    private RedissonClient redis;

    private RQueue<JSONEvent> queue;

    private String topic;

    private Codec codec = new FastjsonCodec();

    @Override
    public boolean send(JSONEvent event) {
        return queue.offer(event);
    }

    @Override
    public boolean sendAsync(JSONEvent event) {
        queue.offerAsync(event);
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
