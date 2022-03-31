package com.example.worksearch.repositories;

import com.example.worksearch.entities.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Employer findEmployerByCompanyName(String companyName);
}
