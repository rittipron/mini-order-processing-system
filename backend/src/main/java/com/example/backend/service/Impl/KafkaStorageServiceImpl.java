package com.example.backend.service.Impl;

import com.example.backend.service.KafkaStorageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaStorageServiceImpl implements KafkaStorageService {
    private final List<String> consumedMessages = new ArrayList<>();

    public void addMessage(String msg) {
        consumedMessages.add(msg);
    }

    public List<String> getAllMessages() {
        return consumedMessages;
    }
}
