package com.example.worksearch.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Clock;
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
    @Getter @Setter
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    @Getter @Setter
    private Vacancy vacancy;

    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDate createdAt;

    public VacancyResponse() {
        this.createdAt = LocalDate.now(Clock.systemUTC());
    }
}
