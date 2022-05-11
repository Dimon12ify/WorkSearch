package com.example.worksearch;

import com.example.worksearch.entities.City;
import com.example.worksearch.repositories.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class WorkSearchApplicationTests {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private CityRepository repository;

    @Test
    public void should_find_no_if_repository_is_empty() {
        var list = repository.findAll();
        assertThat(list).isEmpty();
    }

    @Test
    public void should_find_all_cities() {
        City city1 = new City("Город1");
        City city2 = new City("Город2");

        manager.persistAndFlush(city1);
        manager.persistAndFlush(city2);

        List<City> result = repository.findAll();
        assertThat(result).hasSize(2).containsAll(List.of(city1,city2));
    }

    @Test
    public void should_generate_Id_and_find_single() {
        City city1 = new City();
        city1.setName("Город1");

        manager.persistAndFlush(city1);

        City result = repository.findCityByName("Город1");
        assertThat(result.getId()).isEqualTo(1L);
    }

}
