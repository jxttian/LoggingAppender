package net.myscloud.plugin.logging.logback.redis.appender.delivery;

import net.myscloud.plugin.logging.JSONEvent;
import org.redisson.api.RQueue;

/**
 * 同步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public class BlockingDeliveryStrategy extends DeliveryStrategy {

    @Override
    public void send(RQueue<JSONEvent> queue, JSONEvent event) {
        queue.offer(event);
    }
}
