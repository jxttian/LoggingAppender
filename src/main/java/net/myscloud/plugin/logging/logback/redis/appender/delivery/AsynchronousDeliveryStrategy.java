package net.myscloud.plugin.logging.logback.redis.appender.delivery;

import net.myscloud.plugin.logging.JSONEvent;
import org.redisson.api.RQueue;

/**
 * 异步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public class AsynchronousDeliveryStrategy extends DeliveryStrategy {

    @Override
    public void send(RQueue<JSONEvent> queue, JSONEvent event) {
        queue.offerAsync(event);
    }
}
