package com.example.backend.kafka;

import com.example.backend.model.Order;
import com.example.backend.model.ReportDocument;
import com.example.backend.repository.ReportRepository;
import com.example.backend.service.KafkaStorageService;
import com.example.backend.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class KafkaOrderConsumer {

    @Autowired
    private KafkaStorageService kafkaStorageService;

    @Autowired
    private ReportRepository reportRepo;

    @Autowired
    private final ReportService reportService;

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "order_group")
    public void listen(String message){
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            Order order = mapper.readValue(message, Order.class);

            ReportDocument report = new ReportDocument();
            report.setOrderId(order.getId());
            report.setItem(order.getItem());
            report.setPrice(order.getPrice());
            report.setCreatedAt(order.getCreatedAt());

            reportRepo.save(report);

            // Add Message Kafka Get Show Api
            kafkaStorageService.addMessage(message);

            reportService.generateReportAndCache(order);
            System.out.println("✅ Saved order to MongoDB: " + report.getItem());
        } catch (Exception e) {
            log.error("LOG ERROR : {}", e.getMessage());
        }
    }
}
