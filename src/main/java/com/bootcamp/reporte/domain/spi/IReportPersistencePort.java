package com.bootcamp.reporte.domain.spi;

import com.bootcamp.reporte.domain.model.BootcampReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReportPersistencePort {
    Mono<BootcampReport> save(BootcampReport report);
    Flux<BootcampReport> findAll();
    Mono<BootcampReport> findTopByEnrolledPersonCount();
}
