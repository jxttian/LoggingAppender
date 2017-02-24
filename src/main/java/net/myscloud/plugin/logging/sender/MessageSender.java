package net.myscloud.plugin.logging.sender;

import net.myscloud.plugin.logging.JSONEvent;

/**
 * Created by genesis on 2017/2/24.
 */
public interface MessageSender {

    /**
     * 同步发送
     *
     * @param event
     * @return
     */
    boolean send(JSONEvent event);

    /**
     * 异步发送
     *
     * @param event
     * @return
     */
    boolean sendAsync(JSONEvent event);

    /**
     * 初始化
     */
    void init();

    /**
     * 销毁
     */
    void destroy();
}
