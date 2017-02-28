package net.myscloud.plugin.logging.sender;

import net.myscloud.plugin.logging.LoggerEvent;

/**
 * 消息发送者，处理发送逻辑
 */
public interface MessageSender {

    /**
     * 同步发送
     *
     * @param event
     * @return
     */
    boolean send(LoggerEvent event);

    /**
     * 异步发送
     *
     * @param event
     * @return
     */
    boolean sendAsync(LoggerEvent event);

    /**
     * 同步发送
     *
     * @param topic
     * @param event
     * @return
     */
    boolean send(String topic, LoggerEvent event);

    /**
     * 异步发送
     *
     * @param topic
     * @param event
     * @return
     */
    boolean sendAsync(String topic, LoggerEvent event);

    /**
     * 初始化
     */
    void init();

    /**
     * 销毁
     */
    void destroy();
}
