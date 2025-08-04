package com.example.backend.service;
import java.util.List;

public interface KafkaStorageService {
    void addMessage(String msg);
    List<String> getAllMessages();
}