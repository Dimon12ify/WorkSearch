package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.Vacancy;
import lombok.Getter;

import java.math.BigDecimal;

public class ShortVacancySchema {
    @Getter
    private final long id;

    @Getter
    private final long employerId;

    @Getter
    private final String employerName;

    @Getter
    private final String title;

    @Getter
    private final BigDecimal salary;

    public ShortVacancySchema(
            long id,
            long employerId,
            String employerName,
            String title,
            BigDecimal salary
    ) {
        this.id = id;
        this.employerId = employerId;
        this.employerName = employerName;
        this.title = title;
        this.salary = salary;
    }

    public static ShortVacancySchema fromEntity(Vacancy vacancy){
        return new ShortVacancySchema(
                vacancy.getId(),
                vacancy.getEmployer().getId(),
                vacancy.getEmployer().getCompanyName(),
                vacancy.getTitle(),
                vacancy.getSalary()
        );
    }
}
