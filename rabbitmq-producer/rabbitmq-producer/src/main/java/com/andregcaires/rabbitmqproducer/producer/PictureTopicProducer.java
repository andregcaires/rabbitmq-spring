package com.andregcaires.rabbitmqproducer.producer;

import com.andregcaires.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Topic Exchange
 * In order to send a message to an exchange, one must
 * send 3 parameters on convertAndSend method:
 * the exchange name, routing key, and message.
 * The Routing Key serves as parameters, which are divided by dots (.).
 * In RabbitMQ, there are queues that listen to that routing keys and its parameters,
 * where the special characters * and # may be used to replace one (*) parameter or
 * zero or more (#) parameters
 */
@Service
public class PictureTopicProducer {

    @Autowired
    private RabbitTemplate _rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Picture picture) {

        try {
            var sb = new StringBuilder();

            sb.append(picture.getSource());
            sb.append(".");

            if (picture.getSize() > 4000) {
                sb.append("large");
            } else {
                sb.append("small");
            }            
            sb.append(".");
            sb.append(picture.getType());

            System.out.println("ID sent: "+ picture.getName());
            var json = objectMapper.writeValueAsString(picture);
            var routingKey = sb.toString();

            _rabbitTemplate.convertAndSend("x.picturetopic", routingKey, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}