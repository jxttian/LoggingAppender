package net.myscloud.plugin.logging.logback;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.Consts;
import net.myscloud.plugin.logging.Kits;

import java.text.DateFormat;

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
    private DateFormat df = Consts.DEFAULT_DATEFORMAT;
    /**
     * 日志来源应用IP
     */
    private String host = Kits.getIp();
    /**
     * 字符编码
     */
    private String charset = Consts.DEFAULT_CHARSET;
}
