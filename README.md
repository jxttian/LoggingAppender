# logging-appender

##logback

-- pom.xml添加依赖
```xml
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson</artifactId>
        <version>3.2.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>0.10.1.1</version>
    </dependency>
    <dependency>
        <groupId>net.myscloud.plugin</groupId>
        <artifactId>logging-appender</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
-- logback.xml添加Appender
```xml
    <appender name="Redis" class="net.myscloud.plugin.logging.logback.LogbackAppender">
        <buildStrategy class="net.myscloud.plugin.logging.logback.build.RedisSingleBuildStrategy">
            <config class="net.myscloud.plugin.logging.logback.config.RedisConfig">
                <addresses>127.0.0.1:6379</addresses>
                <topic>redis-log</topic>
            </config>
        </buildStrategy>
        <deliveryStrategy
                class="net.myscloud.plugin.logging.logback.delivery.AsynchronousDeliveryStrategy"/>
        <source>test-application</source>
    </appender>

    <appender name="Kafka" class="net.myscloud.plugin.logging.logback.LogbackAppender">
        <buildStrategy class="net.myscloud.plugin.logging.logback.build.KafkaBuildStrategy">
            <config class="net.myscloud.plugin.logging.logback.config.KafkaConfig">
                <addresses>127.0.0.1:9092</addresses>
                <topic>topic1</topic>
            </config>
        </buildStrategy>
        <deliveryStrategy
                class="net.myscloud.plugin.logging.logback.delivery.AsynchronousDeliveryStrategy"/>
        <source>test-application</source>
    </appender>
```

-- 日志格式
```json
    {
        "@timestamp": "2017-01-17T10:40:53.129+0800",
        "host": "10.2.85.49",
        "level": "WARN",
        "logger": "net.myscloud.plugin.logging.logback.redis.appender.LogbackRedisAppenderTest",
        "message": "test87",
        "source": "test-application",
        "thread": "main"
    }
```

-- https://www.elastic.co/guide/en/logstash/current/plugins-inputs-redis.html#plugins-inputs-redis
