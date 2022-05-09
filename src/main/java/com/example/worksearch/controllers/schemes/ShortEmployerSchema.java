package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.Employer;
import lombok.Getter;

public class ShortEmployerSchema {
    @Getter
    private final long id;

    @Getter
    private final String name;

    @Getter
    private final String cityName;

    public ShortEmployerSchema(long id, String name, String cityName) {
        this.id = id;
        this.name = name;
        this.cityName = cityName;
    }

    public static ShortEmployerSchema FromEntity(Employer employer){
        return new ShortEmployerSchema(
                employer.getId(),
                employer.getCompanyName(),
                employer.getCity().getName()
        );
    }
}
