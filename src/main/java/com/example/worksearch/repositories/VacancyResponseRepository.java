package com.example.worksearch.repositories;

import com.example.worksearch.entities.VacancyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyResponseRepository extends JpaRepository<VacancyResponse, Long> {
    VacancyResponse findByApplicantIdAndVacancyId(long applicantId, long vacancyId);
    List<VacancyResponse> findAllByApplicantId(long applicantId);
    List<VacancyResponse> findAllByVacancyId(long vacancyId);
}
