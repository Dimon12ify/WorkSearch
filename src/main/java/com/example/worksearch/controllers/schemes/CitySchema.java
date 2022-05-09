package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.City;
import lombok.Getter;

public class CitySchema {
    @Getter
    private final long id;

    @Getter
    private final String name;

    public CitySchema(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CitySchema fromEntity(City city){
        return new CitySchema(city.getId(), city.getName());
    }
}
