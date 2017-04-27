package net.myscloud.plugin.logging.logback;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.Consts;
import net.myscloud.plugin.logging.Kits;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * LogbackAppenderConfig
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
abstract class LogbackAppenderConfig<E> extends UnsynchronizedAppenderBase<E> {
    /**
     * 日期格式化
     */
    private DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ", Locale.CHINESE);

    /**
     * 日期格式化
     */
    private DateFormat datetimeFormat = Consts.DEFAULT_DATETIME_FORMAT;
    /**
     * 日志来源应用
     */
    private String source = null;
    /**
     * 日志来源应用IP
     */
    private String host = Kits.getIp();
    /**
     * 字符编码
     */
    private String charset = Consts.DEFAULT_CHARSET;
}
