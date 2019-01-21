package com.mobicule.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.JsonSerializer;



@Configuration
public class KafkaProducerConfiguration {

	
	@Bean
	public ProducerFactory<String, ProducerUserBean> producerFactory()
	{
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, ProducerUserBean>(configs);
		
	}
	
	@Bean
	public KafkaTemplate<String, ProducerUserBean> kafkaTemplate()
	{
		return new KafkaTemplate<String, ProducerUserBean>(producerFactory());
	}
}
