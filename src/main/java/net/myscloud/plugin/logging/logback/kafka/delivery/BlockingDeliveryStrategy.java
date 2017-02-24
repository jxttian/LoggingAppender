package net.myscloud.plugin.logging.logback.kafka.delivery;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import net.myscloud.plugin.logging.JSONEvent;
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

    private long timeout = 0;

    @Override
    public boolean send(Producer<Long, String> producer, JSONEvent event) {
        try {
            final Future<RecordMetadata> future = producer.send(new ProducerRecord<>("", JSON.toJSONString(event)));
            if (timeout > 0L)
                future.get(timeout, TimeUnit.MILLISECONDS);
            else if (timeout == 0) future.get();
            return true;
        } catch (InterruptedException e) {
            return false;
        } catch (ExecutionException | TimeoutException e) {
            addError("BlockingDeliveryStrategy 发送日志失败 : " + e.getMessage());
        }
        return true;
    }
}
