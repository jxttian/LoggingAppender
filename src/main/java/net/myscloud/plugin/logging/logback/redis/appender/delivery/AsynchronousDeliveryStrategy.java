package net.myscloud.plugin.logging.logback.redis.appender.delivery;

import org.redisson.api.RQueue;

/**
 * 异步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public class AsynchronousDeliveryStrategy extends DeliveryStrategy {

    @Override
    public void send(RQueue<String> queue, String massage) {
        queue.offerAsync(massage);
    }
}
