package net.myscloud.plugin.logging.logback.redis.appender;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import net.myscloud.plugin.logging.Consts;
import net.myscloud.plugin.logging.logback.build.BuildStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LogbackRedisAppenderTest {

    @Before
    public void init() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            context.reset();
            configurator.doConfigure(LogbackRedisAppenderTest.class.getResourceAsStream("/logback.xml"));
        } catch (JoranException je) {
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }

    @Test
    public void testRedissionList() {
        Logger logger = LoggerFactory.getLogger(LogbackRedisAppenderTest.class);
        for (int i = 0; i < 1000000; i++) {
            logger.warn("test" + i);
        }
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroy() {
    }

}
