package net.myscloud.plugin.logging.logback.redis.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.IAppender;
import net.myscloud.plugin.logging.JSONEvent;
import org.redisson.Redisson;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class LogbackRedisAppender extends UnsynchronizedAppenderBase<ILoggingEvent> implements IAppender {
    private String address = "127.0.0.1:6379";
    private String password = null;
    private int database = 0;
    private int connectionPoolSize = 10;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");
    private String key = "logstash";
    private String source = null;
    private String type = null;

    private String sourceHost = InetAddress.getLocalHost().toString();

    private RedissonClient redisson;

    private RQueue<String> logstashQueue;

    public LogbackRedisAppender() throws UnknownHostException {
    }

    @Override
    protected void append(ILoggingEvent event) {
        JSONEvent jsonEvent = new JSONEvent();
        jsonEvent.setHost(sourceHost);
        jsonEvent.setLevel(event.getLevel().toString());
        jsonEvent.setSource(source);
        jsonEvent.setMessage(event.getFormattedMessage());
        jsonEvent.setTimestamp(df.format(new Date(event.getTimeStamp())));
        jsonEvent.setLogger(event.getLoggerName());
        jsonEvent.setThread(event.getThreadName());
        IThrowableProxy tp = event.getThrowableProxy();
        if (tp != null) {
            jsonEvent.setThrowable(ThrowableProxyUtil.asString(tp));
        }
        push(JSON.toJSONString(jsonEvent));
    }

    @Override
    public void start() {
        init();
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

    @Override
    public void init() {
        try {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress(address)
                    .setPassword(password)
                    .setDatabase(database)
                    .setConnectionPoolSize(connectionPoolSize);
            redisson = Redisson.create(config);
            logstashQueue = redisson.getQueue(key);
        } catch (Exception e) {
            redisson = null;
        }
    }

    @Override
    public void push(String json) {
        if (redisson == null) {
            return;
        }
        logstashQueue.offerAsync(json);
    }
}
