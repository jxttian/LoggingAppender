package net.myscloud.plugin.logging;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 日志消息实体
 *
 * @author Genesis
 * @since 1.0
 */
@Data
public class JSONEvent {
    private String source;
    private String host;
    private String hostname;
    private String path;
    private String type;
    private List<String> tags;
    private String message;
    @JSONField(name = "@timestamp")
    private String timestamp;
    private String logger;
    private String level;
    private String thread;
    private String throwable;
}
