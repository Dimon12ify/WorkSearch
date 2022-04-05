package com.example.worksearch.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CityJSONParseDTO {

    @Getter @Setter
    public String district;

    @Getter @Setter
    public String name;

    @Getter @Setter
    public long population;

    @Getter @Setter
    public String subject;

    public CityJSONParseDTO(String district, String name, long population, String subject) {
        this.district = district;
        this.name = name;
        this.population = population;
        this.subject = subject;
    }

    public static CityJSONParseDTO toModel(Object obj) {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) obj;
        String district = String.valueOf(map.get("district"));
        String name = String.valueOf(map.get("name"));
        String subject = String.valueOf(map.get("subject"));
        long population = Integer.parseInt(String.valueOf(map.get("population")));
        return new CityJSONParseDTO(district, name, population, subject);
    }
}
