package com.example.worksearch.repositories;

import com.example.worksearch.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Applicant getByFirstNameAndSecondNameAndPatronymic(String firstName, String secondName, String patronymic);
    List<Applicant> getAllByCityName(String cityName);
    Applicant getByContact(String contact);

}
