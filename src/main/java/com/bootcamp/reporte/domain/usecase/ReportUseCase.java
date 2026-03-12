package com.bootcamp.reporte.domain.usecase;

import com.bootcamp.reporte.domain.api.IReportUseCasePort;
import com.bootcamp.reporte.domain.model.BootcampReport;
import com.bootcamp.reporte.domain.spi.IReportPersistencePort;
import reactor.core.publisher.Mono;

public class ReportUseCase implements IReportUseCasePort {

    private final IReportPersistencePort reportPersistencePort;

    public ReportUseCase(IReportPersistencePort reportPersistencePort) {
        this.reportPersistencePort = reportPersistencePort;
    }

    @Override
    public Mono<BootcampReport> saveReport(BootcampReport report) {
        return reportPersistencePort.save(report);
    }

    @Override
    public Mono<BootcampReport> getMostPopularBootcamp() {
        return reportPersistencePort.findTopByEnrolledPersonCount()
                .switchIfEmpty(Mono.error(new RuntimeException("No hay reportes de bootcamps")));
    }
}
