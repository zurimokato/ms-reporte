package com.bootcamp.reporte.domain.usecase;

import com.bootcamp.reporte.domain.model.BootcampReport;
import com.bootcamp.reporte.domain.spi.IReportPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportUseCaseTest {

    @Mock private IReportPersistencePort persistencePort;
    @InjectMocks private ReportUseCase useCase;

    @Test
    @DisplayName("HU-8: Debe guardar reporte de bootcamp")
    void saveReport_ShouldSave() {
        BootcampReport report = new BootcampReport();
        report.setBootcampId(1L); report.setBootcampName("Fullstack");
        report.setCapabilityCount(3); report.setTechnologyCount(10); report.setEnrolledPersonCount(25);

        BootcampReport saved = new BootcampReport();
        saved.setId("abc123"); saved.setBootcampId(1L); saved.setBootcampName("Fullstack");

        when(persistencePort.save(any())).thenReturn(Mono.just(saved));

        StepVerifier.create(useCase.saveReport(report))
                .expectNextMatches(r -> r.getId().equals("abc123"))
                .verifyComplete();
    }

    @Test
    @DisplayName("HU-9: Debe retornar bootcamp con mayor cantidad de personas")
    void getMostPopular_ShouldReturn() {
        BootcampReport report = new BootcampReport();
        report.setId("abc"); report.setBootcampId(1L); report.setBootcampName("Popular");
        report.setEnrolledPersonCount(50);

        when(persistencePort.findTopByEnrolledPersonCount()).thenReturn(Mono.just(report));

        StepVerifier.create(useCase.getMostPopularBootcamp())
                .expectNextMatches(r -> r.getEnrolledPersonCount().equals(50))
                .verifyComplete();
    }

    @Test
    @DisplayName("HU-9: Debe lanzar error cuando no hay reportes")
    void getMostPopular_WhenEmpty_ShouldThrow() {
        when(persistencePort.findTopByEnrolledPersonCount()).thenReturn(Mono.empty());

        StepVerifier.create(useCase.getMostPopularBootcamp())
                .expectError(RuntimeException.class).verify();
    }
}
