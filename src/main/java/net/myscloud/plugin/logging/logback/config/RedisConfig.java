package net.myscloud.plugin.logging.logback.config;

import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.Consts;

/**
 * Redis配置类 部分注释参考{@link org.redisson.config.BaseConfig}
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class RedisConfig {
    /**
     * Redis服务器地址,多个则以`,`分隔
     */
    private String addresses;

    private String topic = Consts.DEFAULT_TOPIC;

    private int database = 0;

    /**
     * 连接池大小，不能小于10
     */
    private int connectionPoolSize = 10;

    private int slaveConnectionPoolSize = 10;

    private int idleConnectionTimeout = 10000;

    private int pingTimeout = 1000;

    private int connectTimeout = 10000;

    private int timeout = 3000;

    private int retryAttempts = 3;

    private int retryInterval = 1500;

    private int reconnectionTimeout = 3000;

    private int failedAttempts = 3;

    private String password;

    private int subscriptionsPerConnection = 5;

    private String masterName = "master";
}