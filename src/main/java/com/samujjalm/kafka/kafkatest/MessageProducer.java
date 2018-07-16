package com.samujjalm.kafka.kafkatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public void sendMessage(String key, String message){
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topicName, key, message);

        if(send.isDone()){
            System.out.println("Message Sent Successfully");
        }

    }

}
