package com.example.backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collation = "order_reports")
public class ReportDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private Long orderId;

    private String item;

    private Double price;

    private LocalDateTime createdAt;
}
