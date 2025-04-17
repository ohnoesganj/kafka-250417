package com.example.kafka.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import com.example.kafka.config.KafkaConfig;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomProducer {

	private final KafkaConfig config;
	private KafkaProducer<String, String> producer = null;

	@PostConstruct
	public void build(){
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getProducer().getKeySerializer());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, config.getProducer().getValueSerializer());
		producer = new KafkaProducer<>(properties);
	}

	public void send(String message) {
		ProducerRecord<String, String> record = new ProducerRecord<>(config.getTemplate().getDefaultTopic(), message);

		producer.send(record, (metadata, exception) -> {
			log.info("publish message: {}", message);
			if (exception!=null){
				log.info(exception.getMessage());
			}
		});
	}
}