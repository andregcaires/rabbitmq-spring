package com.andregcaires.rabbitmqconsumer.consumer;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * When the consumer is slower than the producer (most cases),
 * the concurrency property may be used in the RabbitListener.
 * The number passed will be the number of consumer threads created to 
 * consume from the same producer
 */
@Service
public class FixedRateConsumer {

    private final Logger log = LoggerFactory.getLogger(FixedRateConsumer.class);

    @RabbitListener(queues = "test.fixedrate", concurrency = "3")
    public void listen(final String message) {
        log.info("Consuming {} on thread {}", message, Thread.currentThread().getName());

        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}