package com.bootcamp.reporte.infrastructure.output.mongodb.adapter;

import com.bootcamp.reporte.domain.model.BootcampReport;
import com.bootcamp.reporte.domain.spi.IReportPersistencePort;
import com.bootcamp.reporte.infrastructure.output.mongodb.document.BootcampReportDocument;
import com.bootcamp.reporte.infrastructure.output.mongodb.repository.IBootcampReportRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportMongoAdapter implements IReportPersistencePort {

    private final IBootcampReportRepository repository;

    public ReportMongoAdapter(IBootcampReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<BootcampReport> save(BootcampReport report) {
        return repository.save(toDocument(report)).map(this::toDomain);
    }

    @Override
    public Flux<BootcampReport> findAll() {
        return repository.findAll().map(this::toDomain);
    }

    @Override
    public Mono<BootcampReport> findTopByEnrolledPersonCount() {
        return repository.findTopByOrderByEnrolledPersonCountDesc().map(this::toDomain);
    }

    private BootcampReportDocument toDocument(BootcampReport r) {
        BootcampReportDocument d = new BootcampReportDocument();
        d.setBootcampId(r.getBootcampId()); d.setBootcampName(r.getBootcampName());
        d.setDescription(r.getDescription()); d.setLaunchDate(r.getLaunchDate());
        d.setDurationWeeks(r.getDurationWeeks()); d.setCapabilityCount(r.getCapabilityCount());
        d.setTechnologyCount(r.getTechnologyCount()); d.setEnrolledPersonCount(r.getEnrolledPersonCount());
        d.setCapabilityNames(r.getCapabilityNames()); d.setTechnologyNames(r.getTechnologyNames());
        return d;
    }

    private BootcampReport toDomain(BootcampReportDocument d) {
        BootcampReport r = new BootcampReport();
        r.setId(d.getId()); r.setBootcampId(d.getBootcampId()); r.setBootcampName(d.getBootcampName());
        r.setDescription(d.getDescription()); r.setLaunchDate(d.getLaunchDate());
        r.setDurationWeeks(d.getDurationWeeks()); r.setCapabilityCount(d.getCapabilityCount());
        r.setTechnologyCount(d.getTechnologyCount()); r.setEnrolledPersonCount(d.getEnrolledPersonCount());
        r.setCapabilityNames(d.getCapabilityNames()); r.setTechnologyNames(d.getTechnologyNames());
        return r;
    }
}
