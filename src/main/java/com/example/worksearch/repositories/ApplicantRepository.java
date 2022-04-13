package com.example.worksearch.repositories;

import com.example.worksearch.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Applicant findApplicantByFirstNameAndSecondName(String firstName, String secondName);
    List<Applicant> findAllByCityName(String cityName);
    Applicant findByContact(String contact);
}
