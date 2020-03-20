package com.andregcaires.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedRateProducer {

    @Autowired
    private RabbitTemplate _rabbitTemplate;

    private int i = 0;

    //private Logger log = LoggerFactory.getLogger(FixedRateProducer.class);

    @Scheduled(fixedRate = 500)
    public void sendMessage() {

        i++;
        System.out.println("Sending: " + i);
        _rabbitTemplate.convertAndSend("test.fixedrate", "fixed rate" + i);

    }
}