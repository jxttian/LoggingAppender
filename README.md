# logging-appender
##logback
###redis
-- pom.xml添加依赖
```xml
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson</artifactId>
        <version>3.2.3</version>
    </dependency>
    <dependency>
        <groupId>net.myscloud.plugin</groupId>
        <artifactId>logging-appender</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
-- logback.xml添加Appender
```xml
    <appender name="Redis" class="net.myscloud.plugin.logging.logback.redis.appender.LogbackRedisAppender">
        <redisBuildStrategy class="net.myscloud.plugin.logging.logback.redis.appender.build.SingleBuildStrategy">
            <config class="net.myscloud.plugin.logging.logback.redis.appender.build.RedisConfig">
                <addresses>10.2.81.93:6379</addresses>
            </config>
        </redisBuildStrategy>
        <source>test-application</source>
        <type>test</type>
        <key>redis-log</key>
        <tags>test</tags>
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