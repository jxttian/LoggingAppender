package net.myscloud.plugin.logging.logback.redis.appender.delivery;

import ch.qos.logback.core.spi.ContextAwareBase;
import net.myscloud.plugin.logging.JSONEvent;
import org.redisson.api.RQueue;

/**
 * 日志传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public abstract class DeliveryStrategy extends ContextAwareBase {

    /**
     * 发送日志消息给Redis
     *
     * @param queue
     * @param event
     */
    public abstract void send(RQueue<JSONEvent> queue, JSONEvent event);

}
