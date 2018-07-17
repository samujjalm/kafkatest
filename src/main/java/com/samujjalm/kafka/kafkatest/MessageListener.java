package com.samujjalm.kafka.kafkatest;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class MessageListener {
    public CountDownLatch countDownLatch = new CountDownLatch(1);

    @KafkaListener(topics = "${message.topic.name}", groupId = "test-group-id", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupTest(String message){
        System.out.println(String.format("Received message: %s", message));
        countDownLatch.countDown();
    }

}
