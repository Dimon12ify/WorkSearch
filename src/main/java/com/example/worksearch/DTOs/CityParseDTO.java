package com.example.worksearch.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

public class CityParseDTO {

    @Getter @Setter
    public String district;

    @Getter @Setter
    public String name;

    @Getter @Setter
    public long population;

    @Getter @Setter
    public String subject;

    public CityParseDTO(String district, String name, long population, String subject) {
        this.district = district;
        this.name = name;
        this.population = population;
        this.subject = subject;
    }

    public static CityParseDTO toModel(Object obj) {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) obj;
        var district = String.valueOf(map.get("district"));
        var name = String.valueOf(map.get("name"));
        var subject = String.valueOf(map.get("subject"));
        int population = Integer.parseInt(String.valueOf(map.get("population")));
        return new CityParseDTO(district, name, population, subject);
    }
}
