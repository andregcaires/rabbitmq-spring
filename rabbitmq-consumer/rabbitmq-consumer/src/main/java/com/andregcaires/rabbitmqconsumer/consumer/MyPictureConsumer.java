package com.andregcaires.rabbitmqconsumer.consumer;

import java.io.IOException;

import com.andregcaires.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MyPictureConsumer {

    private final Logger Log = LoggerFactory.getLogger(MyPictureConsumer.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    // error by rejecting message (manual rejection)
    // it requires the following configurations on application.yml (or .properties)
    // listener.direct.acknowledge-mode: manual
    // listener.simple.acknowledge-mode: manual
    @RabbitListener(queues = "q.mypicture.image")
    public void listen(Message message, Channel channel) {

        try {
            var picture = objectMapper.readValue(message.getBody(), Picture.class);
            if (picture.getSize() > 9000) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            }

            Log.info("On my picture: {}", picture.toString());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Error by throwing an exception (automatic rejection)
    //@RabbitListener(queues = "q.mypicture.image")
    public void listen(String message) {

        try {
            var picture = objectMapper.readValue(message, Picture.class);
            if (picture.getSize() > 9000) {
                throw new AmqpRejectAndDontRequeueException("Picture size too large:"+ picture);
            }

            Log.info("On my picture: {}", picture.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}