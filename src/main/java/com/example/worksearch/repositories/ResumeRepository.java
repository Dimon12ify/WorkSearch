package com.example.worksearch.repositories;

import com.example.worksearch.entities.Applicant;
import com.example.worksearch.entities.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume getByApplicantId(Long applicantId);
}
