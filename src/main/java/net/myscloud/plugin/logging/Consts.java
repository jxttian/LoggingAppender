package net.myscloud.plugin.logging;

import com.google.common.base.Charsets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 配置
 *
 * @author Genesis
 * @since 1.0
 */
public interface Consts {

    //默认字符编码
    String DEFAULT_CHARSET = Charsets.UTF_8.name();

    //默认日期格式化
    DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");

    //默认Key
    String DEFAULT_KEY = "log-stash";
}
