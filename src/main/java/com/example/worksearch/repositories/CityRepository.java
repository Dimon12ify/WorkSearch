package com.example.worksearch.repositories;

import com.example.worksearch.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findCityById(Long cityId);
    City findCityByName(String name);
}
