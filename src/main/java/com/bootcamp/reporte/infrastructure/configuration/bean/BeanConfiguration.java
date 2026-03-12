package com.bootcamp.reporte.infrastructure.configuration.bean;

import com.bootcamp.reporte.domain.api.IReportUseCasePort;
import com.bootcamp.reporte.domain.spi.IReportPersistencePort;
import com.bootcamp.reporte.domain.usecase.ReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public IReportUseCasePort reportUseCasePort(IReportPersistencePort persistencePort) {
        return new ReportUseCase(persistencePort);
    }
}
