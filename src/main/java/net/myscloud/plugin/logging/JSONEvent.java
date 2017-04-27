package net.myscloud.plugin.logging;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统日志消息实体
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
@Builder
public class JSONEvent implements LoggerEvent {
    /**
     * 来源应用
     */
    private String source;
    /**
     * 来源应用IP地址
     */
    private String host;

    /**
     * 日志文本
     */
    private String message;

    /**
     * 时间戳
     */
    @JSONField(name = "@timestamp")
    private String timestamp;

    /**
     * 时间
     */
    private String datetime;

    /**
     * 日志输出类名
     */
    private String logger;

    /**
     * 日志级别
     */
    private String level;

    /**
     * 日志输出线程
     */
    private String thread;

    /**
     * 异常堆栈
     */
    private String throwable;
}
