package com.andregcaires.rabbitmqconsumer.consumer;

import java.io.IOException;

import com.andregcaires.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PicturVectorConsumer {

    private final Logger Log = LoggerFactory.getLogger(PicturVectorConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.picture.vector")
    public void listen(String message) {

        try {
            var picture = objectMapper.readValue(message, Picture.class);

            Log.info("On vector: {}", picture.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}