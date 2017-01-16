package net.myscloud.plugin.logging.logback.redis.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.JSONEvent;
import net.myscloud.plugin.logging.logback.redis.appender.build.RedisBuildStrategy;
import net.myscloud.plugin.logging.logback.redis.appender.delivery.DefaultDeliveryStrategy;
import net.myscloud.plugin.logging.logback.redis.appender.delivery.DeliveryStrategy;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;

import java.util.Date;

/**
 * LogbackRedisAppender
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class LogbackRedisAppender extends LogbackRedisAppenderConfig<ILoggingEvent> {

    private RedisBuildStrategy redisBuildStrategy;

    private DeliveryStrategy deliveryStrategy = new DefaultDeliveryStrategy();

    private RedissonClient redisson;

    private RQueue<String> logstashQueue;

    public LogbackRedisAppender() {
        super();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (redisson != null) {
            JSONEvent jsonEvent = new JSONEvent();
            jsonEvent.setHost(this.getSourceHost());
            jsonEvent.setLevel(event.getLevel().toString());
            jsonEvent.setSource(this.getSource());
            jsonEvent.setMessage(event.getFormattedMessage());
            jsonEvent.setTimestamp(this.getDf().format(new Date(event.getTimeStamp())));
            jsonEvent.setLogger(event.getLoggerName());
            jsonEvent.setThread(event.getThreadName());
            IThrowableProxy tp = event.getThrowableProxy();
            if (tp != null) {
                jsonEvent.setThrowable(ThrowableProxyUtil.asString(tp));
            }
            deliveryStrategy.send(logstashQueue, JSON.toJSONString(jsonEvent));
        }
    }

    @Override
    public void start() {
        redisson = redisBuildStrategy.build();
        if (redisson == null) {
            addError("初始化RedissonClient失败");
            return;
        }
        logstashQueue = redisson.getQueue(this.getKey());
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        if (redisson == null || redisson.isShutdown() || redisson.isShuttingDown()) {
            return;
        }
        redisson.shutdown();
    }
}
