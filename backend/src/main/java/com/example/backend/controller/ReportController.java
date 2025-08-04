package com.example.backend.controller;

import com.example.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getReport(@PathVariable long orderId){
        String path = reportService.getReportPath(orderId);
        if(path == null){
            return ResponseEntity.status(400).body("Report not ready or expired.");
        }
        return ResponseEntity.ok(Map.of("report_url",path));
    }

}
