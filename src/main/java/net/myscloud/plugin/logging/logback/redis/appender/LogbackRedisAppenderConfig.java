package net.myscloud.plugin.logging.logback.redis.appender;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.myscloud.plugin.logging.Consts;
import net.myscloud.plugin.logging.Kits;

import java.text.DateFormat;

/**
 * LogbackRedisAppenderConfig
 *
 * @author Genesis
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
abstract class LogbackRedisAppenderConfig<E> extends UnsynchronizedAppenderBase<E> {
    private DateFormat df = Consts.DEFAULT_DATEFORMAT;
    private String key = Consts.DEFAULT_KEY;
    private String source = null;
    private String type = null;
    private String sourceHost = Kits.getIp();
    private String charset = Consts.DEFAULT_CHARSET;
}
