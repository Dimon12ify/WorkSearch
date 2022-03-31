package com.example.worksearch.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;

    @Column(name = "name", nullable = false)
    @Getter @Setter
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @Getter @Setter
    private City city;
}