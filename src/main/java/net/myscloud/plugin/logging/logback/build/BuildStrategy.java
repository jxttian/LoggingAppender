package net.myscloud.plugin.logging.logback.build;

import ch.qos.logback.core.spi.ContextAwareBase;
import net.myscloud.plugin.logging.sender.MessageSender;

/**
 * 构建策略
 *
 * @author Genesis
 * @since 1.0
 */
public abstract class BuildStrategy extends ContextAwareBase {

    /**
     * 消息发送者
     */
    protected static MessageSender sender;

    /**
     * 获取发送者
     *
     * @return
     */
    public static MessageSender sender() {
        return sender;
    }

    /**
     * 构建 {@link MessageSender}
     *
     * @return MessageSender
     */
    public abstract MessageSender build();

}
