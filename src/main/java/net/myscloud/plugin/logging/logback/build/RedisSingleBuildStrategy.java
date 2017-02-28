package net.myscloud.plugin.logging.logback.build;

import com.google.common.base.Splitter;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.logback.config.RedisConfig;
import net.myscloud.plugin.logging.sender.MessageSender;
import net.myscloud.plugin.logging.sender.RedisSender;
import org.redisson.Redisson;
import org.redisson.config.Config;

import java.util.List;

/**
 * 单机Redis构建策略,默认,当前Logstash只支持Redis单机
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class RedisSingleBuildStrategy extends BuildStrategy {

    private RedisConfig config;

    @Override
    public MessageSender build() {
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
                MessageSender sender = RedisSender.builder().redis(Redisson.create(config)).topic(this.getConfig().getTopic()).build();
                BuildStrategy.sender = sender;
                return sender;
            }
        } catch (Exception e) {
            addError("RedisSingleBuildStrategy 构建 RedisSender 失败 : " + e.getMessage());
        }
        return null;
    }
}
