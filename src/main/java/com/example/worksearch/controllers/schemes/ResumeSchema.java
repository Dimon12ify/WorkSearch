package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.Resume;
import lombok.Getter;

import java.time.LocalDate;

public class ResumeSchema {
    @Getter
    private  final long id;

    @Getter
    private final long applicantId;

    @Getter
    private final String applicantName;

    @Getter
    private LocalDate createdAt;

    public ResumeSchema(
            long id,
            long applicantId,
            String applicantName,
            LocalDate createdAt
    ) {
        this.id = id;
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.createdAt = createdAt;
    }

    public static ResumeSchema fromEntity(Resume resume){
        return new ResumeSchema(
                resume.getId(),
                resume.getApplicant().getId(),
                resume.getApplicant().getFullName(),
                resume.getCreatedAt()
        );
    }
}
