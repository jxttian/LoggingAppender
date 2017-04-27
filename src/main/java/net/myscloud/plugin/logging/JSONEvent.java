package net.myscloud.plugin.logging;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 系统日志消息实体
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
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
     * 用于ELK的时间戳
     */
    @JSONField(name = "@timestamp")
    private String datetime;

    /**
     * 时间戳,由于ES持久化默认使用UTC时间,导致java api查询出的时间不正确，故再加一个时间戳
     */
    private Long timestamp;

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

    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public String getDatetime() {
        return Consts.TIMESTAMP_FORMAT.format(new Date(this.getTimestamp()));
    }
}
