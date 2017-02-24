package net.myscloud.plugin.logging.logback.delivery;

import net.myscloud.plugin.logging.JSONEvent;
import net.myscloud.plugin.logging.sender.MessageSender;

/**
 * 异步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public class AsynchronousDeliveryStrategy extends DeliveryStrategy {
    @Override
    public boolean send(MessageSender sender, JSONEvent event) {
        return sender.sendAsync(event);
    }
}
