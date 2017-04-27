package net.myscloud.plugin.logging.logback.redis.appender;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import net.myscloud.plugin.logging.Consts;
import net.myscloud.plugin.logging.LoggerEvent;

import java.util.Date;

/**
 * Created by genesis on 2017/2/28.
 */
@Data
@Builder
public class TestEvent implements LoggerEvent {

    private String type;

    private String test;

    /**
     * 时间戳
     */
    @JSONField(name = "@timestamp")
    private String datetime;

    private Long timestamp;

    @Override
    public String getDatetime() {
        return Consts.TIMESTAMP_FORMAT.format(new Date(this.getTimestamp()));
    }
}
