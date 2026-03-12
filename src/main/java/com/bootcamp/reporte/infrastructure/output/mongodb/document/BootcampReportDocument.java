package com.bootcamp.reporte.infrastructure.output.mongodb.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "bootcamp_reports")
public class BootcampReportDocument {
    @Id
    private String id;
    private Long bootcampId;
    private String bootcampName;
    private String description;
    private LocalDate launchDate;
    private Integer durationWeeks;
    private Integer capabilityCount;
    private Integer technologyCount;
    private Integer enrolledPersonCount;
    private List<String> capabilityNames;
    private List<String> technologyNames;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getBootcampId() { return bootcampId; }
    public void setBootcampId(Long bootcampId) { this.bootcampId = bootcampId; }
    public String getBootcampName() { return bootcampName; }
    public void setBootcampName(String bootcampName) { this.bootcampName = bootcampName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getLaunchDate() { return launchDate; }
    public void setLaunchDate(LocalDate launchDate) { this.launchDate = launchDate; }
    public Integer getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(Integer durationWeeks) { this.durationWeeks = durationWeeks; }
    public Integer getCapabilityCount() { return capabilityCount; }
    public void setCapabilityCount(Integer capabilityCount) { this.capabilityCount = capabilityCount; }
    public Integer getTechnologyCount() { return technologyCount; }
    public void setTechnologyCount(Integer technologyCount) { this.technologyCount = technologyCount; }
    public Integer getEnrolledPersonCount() { return enrolledPersonCount; }
    public void setEnrolledPersonCount(Integer enrolledPersonCount) { this.enrolledPersonCount = enrolledPersonCount; }
    public List<String> getCapabilityNames() { return capabilityNames; }
    public void setCapabilityNames(List<String> capabilityNames) { this.capabilityNames = capabilityNames; }
    public List<String> getTechnologyNames() { return technologyNames; }
    public void setTechnologyNames(List<String> technologyNames) { this.technologyNames = technologyNames; }
}
