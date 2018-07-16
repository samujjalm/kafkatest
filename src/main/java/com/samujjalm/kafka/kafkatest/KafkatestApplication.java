package com.samujjalm.kafka.kafkatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkatestApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(KafkatestApplication.class, args);
		MessageProducer messageProducer = context.getBean(MessageProducer.class);
		MessageListener messageListener = context.getBean(MessageListener.class);

		messageProducer.sendMessage(String.valueOf(new Random().nextInt(100)),"Deadly message");
		messageProducer.sendMessage(String.valueOf(new Random().nextInt(100)),"Robust message");
		Thread.sleep(2000);

		messageListener.countDownLatch.await(5, TimeUnit.SECONDS);


		while (messageListener.countDownLatch.getCount() != 0){}
		context.close();
	}

	@Bean
	public MessageProducer messageProducer(){
		return new MessageProducer();
	}

	@Bean
	public MessageListener messageListener(){
		return new MessageListener();
	}

}
