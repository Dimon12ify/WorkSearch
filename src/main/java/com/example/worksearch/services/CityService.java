package com.example.worksearch.services;

import com.example.worksearch.entities.City;
import com.example.worksearch.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository repository;

    @Autowired
    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public City save(City city) {
        City existEntity = repository.findCityByName(city.getName());
        City savedEntity;
        if (existEntity == null)
            savedEntity = repository.save(city);
        else
            throw new IllegalArgumentException("City with name " + city.getName() + " is already exists");
        return savedEntity;
    }
}
