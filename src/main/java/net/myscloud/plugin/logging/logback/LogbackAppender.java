package net.myscloud.plugin.logging.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.JSONEvent;
import net.myscloud.plugin.logging.logback.build.BuildStrategy;
import net.myscloud.plugin.logging.logback.delivery.AsynchronousDeliveryStrategy;
import net.myscloud.plugin.logging.logback.delivery.DeliveryStrategy;
import net.myscloud.plugin.logging.sender.MessageSender;

import java.util.Date;

/**
 * LogbackAppender
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class LogbackAppender extends LogbackAppenderConfig<ILoggingEvent> {

    private BuildStrategy buildStrategy;

    private DeliveryStrategy deliveryStrategy = new AsynchronousDeliveryStrategy();

    private MessageSender sender;

    public LogbackAppender() {
        super();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (sender != null) {
            IThrowableProxy tp = event.getThrowableProxy();
            deliveryStrategy.send(sender, JSONEvent.builder().host(this.getHost())
                    .level(event.getLevel().toString())
                    .source(this.getSource())
                    .message(event.getFormattedMessage())
                    .timestamp(event.getTimeStamp())
                    .logger(event.getLoggerName())
                    .thread(event.getThreadName())
                    .throwable(tp == null ? null : ThrowableProxyUtil.asString(tp)).build());
        }
    }

    @Override
    public void start() {
        addInfo("LogbackAppender 启动");
        sender = buildStrategy.build();
        if (sender != null) {
            sender.init();
        }
        super.start();
    }

    @Override
    public void stop() {
        addInfo("LogbackAppender 停止");
        super.stop();
        if (sender != null) {
            sender.destroy();
        }
    }
}
