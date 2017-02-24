package net.myscloud.plugin.logging.logback.delivery;

import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.JSONEvent;
import net.myscloud.plugin.logging.sender.MessageSender;

/**
 * 同步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class BlockingDeliveryStrategy extends DeliveryStrategy {

    @Override
    public boolean send(MessageSender sender, JSONEvent event) {
        return sender.send(event);
    }
}
