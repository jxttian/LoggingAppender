package net.myscloud.plugin.logging;

/**
 * Created by genesis on 16/4/14.
 */
public interface IAppender {

    /**
     * 初始化
     */
    void init();

    void push(String json);

}
