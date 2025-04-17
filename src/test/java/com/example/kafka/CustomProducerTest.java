package com.example.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.kafka.service.CustomProducer;

@SpringBootTest
public class CustomProducerTest {

	@Autowired
	private CustomProducer customProducer;

	@Test
	public void test() {
		customProducer.send("this message sent from spring boot application");
	}
}
