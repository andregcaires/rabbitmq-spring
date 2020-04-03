package com.andregcaires.rabbitmqproducer.producer;

import com.andregcaires.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPictureProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture picture) {
        var json = objectMapper.writeValueAsString(picture);

        rabbitTemplate.convertAndSend("x.mypicture", json);
    }
}