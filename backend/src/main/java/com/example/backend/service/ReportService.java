package com.example.backend.service;

import com.example.backend.model.Order;

public interface ReportService {
    void generateReportAndCache(Order order);
    String getReportPath(Long orderId);
}