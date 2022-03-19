package com.example.worksearch.entities;

import com.example.worksearch.entities.enums.VacancyState;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    @Getter @Setter
    private Employer employer;

    @Column(name = "title", nullable = false)
    @Getter @Setter
    private String title;

    @Column(name = "description", nullable = false)
    @Getter @Setter
    private String description;

    @Column(name = "salary", nullable = false)
    @Getter @Setter
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @Getter @Setter
    private City city;

    @Enumerated(EnumType.STRING)
    @Getter
    private VacancyState state;

    @Column(name = "created_at", nullable = false)
    @Getter
    private LocalDate createdAt;

    @Column(name = "published_at")
    @Getter
    private LocalDate publishedAt;

    @Column(name = "assigned_at")
    @Getter
    private LocalDate assignedAt;

    @Column(name = "closed_at")
    @Getter
    private LocalDate closedAt;

    public void setStateOpen(){
        if (this.state != VacancyState.DRAFT){
            throw new IllegalStateException(
                    "Only DRAFT vacancies could be published!"
            );
        }

        this.state = VacancyState.OPEN;
        this.publishedAt = LocalDate.now();
    }

    public void setStateAssigned(){
        if (this.state != VacancyState.OPEN){
            throw new IllegalStateException(
                    "Only OPEN vacancies could be assigned!"
            );
        }

        this.state = VacancyState.ASSIGNED;
        this.assignedAt = LocalDate.now();
    }

    public void setStateClosed(){
        if (this.state != VacancyState.OPEN){
            throw new IllegalStateException(
                    "Only OPEN vacancies could be closed!"
            );
        }

        this.state = VacancyState.CLOSED;
        this.closedAt = LocalDate.now();
    }
}