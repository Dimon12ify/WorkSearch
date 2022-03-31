package com.example.worksearch.services;

import com.example.worksearch.entities.City;
import com.example.worksearch.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository repository;

    @Autowired
    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public void save(City city) {
        City savedCity = repository.findCityByName(city.getName());
        if (savedCity == null)
            repository.save(city);
        else
            throw new IllegalArgumentException("City with name " + city.getName() + " is already exists");
    }
}
