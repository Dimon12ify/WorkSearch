package com.example.worksearch.services;

import com.example.worksearch.DTOs.CityParseDTO;
import com.example.worksearch.entities.City;
import com.example.worksearch.repositories.CityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository repository;

    @Autowired
    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public City save(@NotNull City city) {
        City existEntity = repository.findCityByName(city.getName());
        City savedEntity;
        if (existEntity == null)
            savedEntity = repository.save(city);
        else
            throw new IllegalArgumentException("City with name " + city.getName() + " is already exists");
        return savedEntity;
    }

    public City getByName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Blank or empty name");
        }
        return repository.findCityByName(name);
    }

    public List<City> getAll() {
        return repository.findAll();
    }

    public City getById(Long id) {
        return repository.getById(id);
    }

    public List<String> getAllNames() {
        return repository.findAll().stream().map(City::getName).collect(Collectors.toList());
    }

    public Set<String> getUnsaved() throws IOException, ParseException {
        Resource resource = new ClassPathResource("russian-cities.json");
        FileInputStream file = new FileInputStream(resource.getFile());
        JSONParser parser = new JSONParser(file);
        ArrayList<Object> a = (ArrayList<Object>) parser.parse();
        List<CityParseDTO> list = a.stream().map(CityParseDTO::toModel).collect(Collectors.toList());
        var cities =  list.stream().map(f -> f.name).collect(Collectors.toList());
        var savedCities = repository.findAll().stream()
                .map(City::getName)
                .collect(Collectors.toList());
        return cities.stream()
                .filter(f -> !savedCities.contains(f))
                .collect(Collectors.toSet());
    }
}
