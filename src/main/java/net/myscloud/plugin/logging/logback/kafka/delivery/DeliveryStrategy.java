package net.myscloud.plugin.logging.logback.kafka.delivery;

import ch.qos.logback.core.spi.ContextAwareBase;
import net.myscloud.plugin.logging.JSONEvent;
import org.apache.kafka.clients.producer.Producer;

/**
 * 日志传输策略Kafka
 *
 * @author Genesis
 * @since 1.0
 */
public abstract class DeliveryStrategy extends ContextAwareBase {

    /**
     * 发送日志到Kafka
     *
     * @param producer
     * @param event
     * @return
     */
    public abstract boolean send(Producer<Long, String> producer, JSONEvent event);

}
