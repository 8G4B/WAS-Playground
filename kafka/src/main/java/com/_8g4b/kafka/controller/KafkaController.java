package com._8g4b.kafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com._8g4b.kafka.producer.MessageProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final MessageProducer messageProducer;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        messageProducer.sendMessage("demo-topic", message);
        return "Message published successfully";
    }
}
