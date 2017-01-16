package net.myscloud.plugin.logging.logback.redis.appender.build;

import ch.qos.logback.core.spi.ContextAwareBase;
import lombok.Data;
import org.redisson.api.RedissonClient;

/**
 * Redis构建策略，如单机，哨兵，集群等
 *
 * @author Genesis
 * @since 1.0
 */
@Data
public abstract class RedisBuildStrategy extends ContextAwareBase {

    private RedisConfig config;

    /**
     * 构建 {@link RedissonClient}
     *
     * @return
     */
    public abstract RedissonClient build();

}
