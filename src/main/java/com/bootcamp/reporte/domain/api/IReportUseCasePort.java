package com.bootcamp.reporte.domain.api;

import com.bootcamp.reporte.domain.model.BootcampReport;
import reactor.core.publisher.Mono;

public interface IReportUseCasePort {
    Mono<BootcampReport> saveReport(BootcampReport report);
    Mono<BootcampReport> getMostPopularBootcamp();
}
