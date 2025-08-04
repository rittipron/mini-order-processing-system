package com.example.backend.controller;

import com.example.backend.service.KafkaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private KafkaStorageService kafkaStorageService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/kafka")
    public ResponseEntity<List<String>> getAllKafkaMessages() {
        return ResponseEntity.ok(kafkaStorageService.getAllMessages());
    }

    @GetMapping("/redis")
    public ResponseEntity<Map<String, String>> getAllRedisData() {
        Set<String> keys = redisTemplate.keys("*");

        if (keys.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyMap());
        }

        Map<String, String> result = new HashMap<>();
        for (String key : keys) {
            String value = redisTemplate.opsForValue().get(key);
            result.put(key, value);
        }

        return ResponseEntity.ok(result);
    }
}
