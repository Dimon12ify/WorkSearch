package com.example.worksearch.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private long id;

    @Getter @Setter
    private String name;
}