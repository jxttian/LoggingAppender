package net.myscloud.plugin.logging.logback.config;

import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.Consts;

/**
 * Kafka配置类 {@link org.apache.kafka.clients.producer.ProducerConfig}
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class KafkaConfig {
    /**
     * 服务器地址
     */
    private String addresses;

    private String topic = Consts.DEFAULT_TOPIC;
    private String client = Consts.DEFAULT_KAFKA_CLIENT;
    private String keySerializer = Consts.DEFAULT_KEY_SERIALIZER;
    private String valueSerializer = Consts.DEFAULT_VALUE_SERIALIZER;
    private int connectionTimeout = Consts.DEFAULT_CONNECTION_TIMEOUT;
}
