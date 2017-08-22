package net.myscloud.plugin.logging.logback.build;

import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.logback.config.KafkaConfig;
import net.myscloud.plugin.logging.sender.KafkaSender;
import net.myscloud.plugin.logging.sender.MessageSender;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * Kafka构建策略
 */
@Getter
@Setter
public class KafkaBuildStrategy extends BuildStrategy {

    private KafkaConfig config;

    @Override
    public MessageSender build() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.getConfig().getAddresses());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, this.getConfig().getClient());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.getConfig().getConnectionTimeout());
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, this.getConfig().getMaxBlockMs());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        MessageSender sender = KafkaSender.builder().topic(this.getConfig().getTopic()).producer(new KafkaProducer<>(props)).build();
        BuildStrategy.sender = sender;
        return sender;
    }
}
