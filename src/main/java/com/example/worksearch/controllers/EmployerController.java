package com.example.worksearch.controllers;

import com.example.worksearch.controllers.schemes.CreateEmployerSchema;
import com.example.worksearch.controllers.schemes.DetailedEmployerSchema;
import com.example.worksearch.controllers.schemes.ShortEmployerSchema;
import com.example.worksearch.entities.City;
import com.example.worksearch.entities.Employer;
import com.example.worksearch.services.CityService;
import com.example.worksearch.services.EmployerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/employer")
public class EmployerController {
    private final EmployerService _employerService;
    private final CityService _cityService;

    public EmployerController(EmployerService _employerService, CityService cityServise) {
        this._employerService = _employerService;
        _cityService = cityServise;
    }

    @GetMapping("all")
    public List<ShortEmployerSchema> all(
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int perPage
    ) {
        List<Employer> employers = _employerService.getAll(page, perPage);
        return employers.stream()
                .map(ShortEmployerSchema::FromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("id/{id}")
    public DetailedEmployerSchema getById(@PathVariable Long id) {
        Employer employer = _employerService.getById(id);
        return DetailedEmployerSchema.fromEntity(employer);
    }

    @PostMapping("add")
    public DetailedEmployerSchema create(@RequestBody CreateEmployerSchema employerToCreate) {
        Employer employer = new Employer();
        employer.setCompanyName(employerToCreate.getName());

        City city = _cityService.getById(employerToCreate.getCityId());
        employer.setCity(city);

        employer = _employerService.save(employer);
        return DetailedEmployerSchema.fromEntity(employer);
    }
}
