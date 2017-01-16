package net.myscloud.plugin.logging.logback.redis.appender.delivery;

import ch.qos.logback.core.spi.ContextAwareBase;
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
     * @param massage
     */
    public abstract void send(RQueue<String> queue, String massage);

}
