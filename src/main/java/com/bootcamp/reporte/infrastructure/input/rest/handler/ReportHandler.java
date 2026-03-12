package com.bootcamp.reporte.infrastructure.input.rest.handler;

import com.bootcamp.reporte.domain.api.IReportUseCasePort;
import com.bootcamp.reporte.domain.model.BootcampReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ReportHandler {

    private final IReportUseCasePort reportUseCasePort;

    public ReportHandler(IReportUseCasePort reportUseCasePort) {
        this.reportUseCasePort = reportUseCasePort;
    }

    public Mono<ServerResponse> saveReport(ServerRequest request) {
        return request.bodyToMono(BootcampReport.class)
                .flatMap(reportUseCasePort::saveReport)
                .flatMap(r -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(r));
    }

    public Mono<ServerResponse> getMostPopular(ServerRequest request) {
        return reportUseCasePort.getMostPopularBootcamp()
                .flatMap(r -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(r));
    }
}
