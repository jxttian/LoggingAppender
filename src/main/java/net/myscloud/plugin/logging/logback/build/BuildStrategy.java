package net.myscloud.plugin.logging.logback.build;

import ch.qos.logback.core.spi.ContextAwareBase;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.sender.MessageSender;

/**
 * 构建策略
 *
 * @author Genesis
 * @since 1.0
 */
public abstract class BuildStrategy extends ContextAwareBase {
    /**
     * 构建 {@link MessageSender}
     *
     * @return MessageSender
     */
    public abstract MessageSender build();

}
