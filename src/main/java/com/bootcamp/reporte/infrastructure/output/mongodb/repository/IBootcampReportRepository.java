package com.bootcamp.reporte.infrastructure.output.mongodb.repository;

import com.bootcamp.reporte.infrastructure.output.mongodb.document.BootcampReportDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IBootcampReportRepository extends ReactiveMongoRepository<BootcampReportDocument, String> {
    Mono<BootcampReportDocument> findTopByOrderByEnrolledPersonCountDesc();
}
