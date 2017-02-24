package net.myscloud.plugin.logging.logback.delivery;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.JSONEvent;
import net.myscloud.plugin.logging.sender.MessageSender;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 同步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
@Getter
@Setter
public class BlockingDeliveryStrategy extends DeliveryStrategy {

    @Override
    public boolean send(MessageSender sender, JSONEvent event) {
        return sender.send(event);
    }
}
