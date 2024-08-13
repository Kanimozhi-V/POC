package com.implementation.SM.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_lead")
    private String lead;

    @Column(name = "tech_stack")
    private String techStack;

    @Column(name = "project_status")
    private String status;

    @Column(name = "project_startdate")
    private LocalDate startdate;

    @Column(name = "project_deadline")
    private LocalDate deadline;

    @Column(name = "total_people_allocated")
    private int totalPeopleAllocated;

    @Column(name = "actual_allocation_sow")
    private int actualAllocationSOW;

    @Enumerated(EnumType.STRING)
    @Column(name = "adr_item_documentation")
    private StatusEnum adrItemDocumentation;

    @Enumerated(EnumType.STRING)
    @Column(name = "c4_documentation")
    private StatusEnum c4Documentation;

    @Enumerated(EnumType.STRING)
    @Column(name = "architecture_handover")
    private StatusEnum architectureHandover;

    @Enumerated(EnumType.STRING)
    @Column(name = "decoders_repository")
    private StatusEnum decodersRepository;

    @Enumerated(EnumType.STRING)
    @Column(name = "process_adoption_latest_version")
    private StatusEnum processAdoptionLatestVersion;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_kickoff")
    private StatusEnum projectKickoff;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_shared_document_repository")
    private StatusEnum projectSharedDocumentRepository;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_handover")
    private StatusEnum salesHandover;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Status> statuses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

//    public List<String> getTechStackList() {
//        return Arrays.asList(techStack.split(","));
//    }
//
//    public void setTechStackList(List<String> techStack) {
//        this.techStack = String.join(",", techStack);
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getTotalPeopleAllocated() {
        return totalPeopleAllocated;
    }

    public void setTotalPeopleAllocated(int totalPeopleAllocated) {
        this.totalPeopleAllocated = totalPeopleAllocated;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

    public int getActualAllocationSOW() {
        return actualAllocationSOW;
    }

    public void setActualAllocationSOW(int actualAllocationSOW) {
        this.actualAllocationSOW = actualAllocationSOW;
    }

    public StatusEnum getAdrItemDocumentation() {
        return adrItemDocumentation;
    }

    public void setAdrItemDocumentation(StatusEnum adrItemDocumentation) {
        this.adrItemDocumentation = adrItemDocumentation;
    }

    public StatusEnum getC4Documentation() {
        return c4Documentation;
    }

    public void setC4Documentation(StatusEnum c4Documentation) {
        this.c4Documentation = c4Documentation;
    }

    public StatusEnum getArchitectureHandover() {
        return architectureHandover;
    }

    public void setArchitectureHandover(StatusEnum architectureHandover) {
        this.architectureHandover = architectureHandover;
    }

    public StatusEnum getDecodersRepository() {
        return decodersRepository;
    }

    public void setDecodersRepository(StatusEnum decodersRepository) {
        this.decodersRepository = decodersRepository;
    }

    public StatusEnum getProcessAdoptionLatestVersion() {
        return processAdoptionLatestVersion;
    }

    public void setProcessAdoptionLatestVersion(StatusEnum processAdoptionLatestVersion) {
        this.processAdoptionLatestVersion = processAdoptionLatestVersion;
    }

    public StatusEnum getProjectKickoff() {
        return projectKickoff;
    }

    public void setProjectKickoff(StatusEnum projectKickoff) {
        this.projectKickoff = projectKickoff;
    }

    public StatusEnum getProjectSharedDocumentRepository() {
        return projectSharedDocumentRepository;
    }

    public void setProjectSharedDocumentRepository(StatusEnum projectSharedDocumentRepository) {
        this.projectSharedDocumentRepository = projectSharedDocumentRepository;
    }

    public StatusEnum getSalesHandover() {
        return salesHandover;
    }

    public void setSalesHandover(StatusEnum salesHandover) {
        this.salesHandover = salesHandover;
    }
}