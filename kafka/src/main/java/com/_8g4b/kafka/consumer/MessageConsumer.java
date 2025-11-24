package com._8g4b.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageConsumer {

    @KafkaListener(topics = "demo-topic", groupId = "kafka-group")
    public void consume(String message) {
        log.info("Consumed message: {}", message);
    }
}
