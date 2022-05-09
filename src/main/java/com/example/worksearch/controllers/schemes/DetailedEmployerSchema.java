package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.Employer;
import lombok.Getter;

public class DetailedEmployerSchema {
    @Getter
    private final long id;

    @Getter
    private final String name;

    @Getter
    private final CitySchema city;

    public DetailedEmployerSchema(long id, String name, CitySchema city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public static DetailedEmployerSchema fromEntity(Employer employer){
        return new DetailedEmployerSchema(
                employer.getId(),
                employer.getCompanyName(),
                CitySchema.fromEntity(employer.getCity())
        );
    }
}
