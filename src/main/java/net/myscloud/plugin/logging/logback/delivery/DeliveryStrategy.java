package net.myscloud.plugin.logging.logback.delivery;

import ch.qos.logback.core.spi.ContextAwareBase;
import net.myscloud.plugin.logging.JSONEvent;
import net.myscloud.plugin.logging.sender.MessageSender;
import org.apache.kafka.clients.producer.Producer;

/**
 * 日志传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public abstract class DeliveryStrategy extends ContextAwareBase {

    /**
     * 发送日志
     *
     * @param sender
     * @param event
     * @return
     */
    public abstract boolean send(MessageSender sender, JSONEvent event);

}
