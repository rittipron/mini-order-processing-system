package com.example.backend.repository;

import com.example.backend.model.ReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<ReportDocument, String> {

}
