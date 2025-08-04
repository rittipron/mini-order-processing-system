package com.example.backend.dto.request;
import lombok.Data;

@Data
public class OrderRequest {
    private String user;
    private String item;
    private int price;
}
