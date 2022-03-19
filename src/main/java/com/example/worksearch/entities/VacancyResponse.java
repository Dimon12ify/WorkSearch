package com.example.worksearch.entities;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class VacancyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    @Getter
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    @Getter
    private Vacancy vacancy;

    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDate createdAt;
}
