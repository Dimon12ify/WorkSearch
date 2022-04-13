package com.example.worksearch.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Employer> employers;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<Applicant> applicants;

    public City(String name) {
        this.name = name;
    }

    public City() {

    }
}