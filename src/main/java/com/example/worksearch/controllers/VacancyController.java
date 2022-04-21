package com.example.worksearch.controllers;

import com.example.worksearch.entities.Vacancy;
import com.example.worksearch.services.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vacancy")
public class VacancyController {

    @Autowired
    private VacancyService service;

    @GetMapping("all")
    public List<Vacancy> getAll() {
        return service.getAll();
    }

    @GetMapping("state/{vacancyState}")
    public List<Vacancy> getAll(@PathVariable String vacancyState) {
        return service.getAllByVacancyState(vacancyState);
    }

    @GetMapping("id/{id}")
    public Vacancy getById(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping("title/{title}")
    public Vacancy getById(@PathVariable String title) {
        return service.getByTitle(title);
    }

    @PostMapping("{id}/edit")
    public String edit(Vacancy vacancy, @PathVariable long id) {
        vacancy.setId(id);
        service.edit(vacancy);
        return "Ok";
    }
}
