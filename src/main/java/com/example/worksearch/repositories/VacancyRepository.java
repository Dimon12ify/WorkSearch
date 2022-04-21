package com.example.worksearch.repositories;

import com.example.worksearch.entities.Vacancy;
import com.example.worksearch.entities.enums.VacancyState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Vacancy getByTitle(String title);

    List<Vacancy> getAllByCityId(long cityId);

    List<Vacancy> getAllByState(VacancyState state);
}
