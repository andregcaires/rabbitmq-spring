package com.andregcaires.rabbitmqproducer.producer;

import com.andregcaires.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Direct Exchange
 * In order to send a message to an exchange, one must
 * send 3 parameters on convertAndSend method:
 * the exchange name, routing key, and message.
 * In this example, there was no routing key configured, 
 * so an empty string will be sent
 */
@Service
public class PictureProducer {

    @Autowired
    private RabbitTemplate _rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture picture) {

        try {
            System.out.println("ID sent: "+ picture.getName());
            var json = objectMapper.writeValueAsString(picture);

            _rabbitTemplate.convertAndSend("x.picture", picture.getType(), json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}