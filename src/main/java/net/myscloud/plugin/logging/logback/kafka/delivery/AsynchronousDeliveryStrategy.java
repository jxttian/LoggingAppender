package net.myscloud.plugin.logging.logback.kafka.delivery;

import com.alibaba.fastjson.JSON;
import net.myscloud.plugin.logging.JSONEvent;
import org.apache.kafka.clients.producer.BufferExhaustedException;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 同步传输策略
 *
 * @author Genesis
 * @since 1.0
 */
public class AsynchronousDeliveryStrategy extends DeliveryStrategy {

    @Override
    public boolean send(Producer<Long, String> producer, JSONEvent event) {
        producer.send(new ProducerRecord<>("", JSON.toJSONString(event)), (metadata, exception) -> {
            if (exception != null) {
                addError("AsynchronousDeliveryStrategy 发送日志失败 : " + exception.getMessage());
            }
        });
        return true;
    }
}
