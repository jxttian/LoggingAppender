package net.myscloud.plugin.logging.logback.redis.appender.build;

import com.google.common.base.Splitter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.List;

/**
 * 单机Redis构建策略
 *
 * @author Genesis
 * @since 1.0
 */
public class SingleBuildStrategy extends RedisBuildStrategy {
    @Override
    public RedissonClient build() {
        try {
            List<String> addresses = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(this.getConfig().getAddresses());
            if (addresses.size() > 0) {
                Config config = new Config();
                config.useSingleServer()
                        .setAddress(addresses.get(0))
                        .setPassword(this.getConfig().getPassword())
                        .setDatabase(this.getConfig().getDatabase())
                        .setConnectTimeout(this.getConfig().getConnectTimeout())
                        .setReconnectionTimeout(this.getConfig().getReconnectionTimeout())
                        .setRetryInterval(this.getConfig().getRetryInterval())
                        .setFailedAttempts(this.getConfig().getFailedAttempts())
                        .setRetryAttempts(this.getConfig().getRetryAttempts())
                        .setPingTimeout(this.getConfig().getPingTimeout())
                        .setConnectionPoolSize(this.getConfig().getConnectionPoolSize());
                return Redisson.create(config);
            }
        } catch (Exception e) {
            addError("SingleBuildStrategy 构建 RedissonClient 失败 : " + e.getMessage());
        }
        return null;
    }
}
