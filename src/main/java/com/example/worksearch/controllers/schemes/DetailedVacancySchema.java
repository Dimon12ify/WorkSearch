package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.Vacancy;
import com.example.worksearch.entities.enums.VacancyState;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DetailedVacancySchema {
    // TODO: разделить метод получения вакансии на методы для
    //  соискателя и работодателя. Так как по-хорошему им надо
    //  показывать разные поля (например, даты)

    @Getter
    private final long id;

    @Getter
    private final ShortEmployerSchema employer;

    @Getter
    private final String title;

    @Getter
    private final String description;

    @Getter
    private final BigDecimal salary;

    @Getter
    private final CitySchema city;

    @Getter
    @Enumerated(EnumType.STRING)
    private final VacancyState state;

    @Getter
    private final LocalDate publishedAt;

    public DetailedVacancySchema(
            long id,
            ShortEmployerSchema employer,
            String title,
            String description,
            BigDecimal salary,
            CitySchema city,
            VacancyState state,
            LocalDate publishedAt
    ) {
        this.id = id;
        this.employer = employer;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.city = city;
        this.state = state;
        this.publishedAt = publishedAt;
    }


    public static DetailedVacancySchema fromEntity(Vacancy vacancy){
        return new DetailedVacancySchema(
                vacancy.getId(),
                ShortEmployerSchema.FromEntity(vacancy.getEmployer()),
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getSalary(),
                CitySchema.fromEntity(vacancy.getCity()),
                vacancy.getState(),
                vacancy.getPublishedAt()
        );
    }
}
