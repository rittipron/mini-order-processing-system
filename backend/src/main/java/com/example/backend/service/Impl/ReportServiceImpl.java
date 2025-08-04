package com.example.backend.service.Impl;

import com.example.backend.model.Order;
import com.example.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void generateReportAndCache(Order order){
        String fakePdfPath = "/reports/report_" + order.getId() + ".pdf";

        String redisKey = "report:" + order.getId();
        redisTemplate.opsForValue().set(redisKey, fakePdfPath, Duration.ofMinutes(60));

        System.out.println("Report Path Cached:" + fakePdfPath);
    }

    public String getReportPath(Long orderId){
        String rediskey = "report:" + orderId;
        return redisTemplate.opsForValue().get(rediskey);
    }
}
