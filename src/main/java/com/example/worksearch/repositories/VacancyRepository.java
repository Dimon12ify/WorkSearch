package com.example.worksearch.repositories;

import com.example.worksearch.entities.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Vacancy findVacancyByTitle(String title);
    List<Vacancy> findAllByCityName(String cityName);
}
