package com.andregcaires.rabbitmqconsumer.consumer;

import java.io.IOException;

import com.andregcaires.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PictureVectorConsumer {

    private final Logger Log = LoggerFactory.getLogger(PictureVectorConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "q.picturetopic.vector")
    public void listen(String message) {

        try {
            var picture = objectMapper.readValue(message, Picture.class);

            Log.info("On topic / vector: {}", picture.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}