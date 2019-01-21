package com.mobicule.main;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@KafkaListener(topics="ConsumerDemo",groupId="groupId")
	public void consume(String message)
	{
		System.out.println("In kafka consumer "+message);
	}
	
}
