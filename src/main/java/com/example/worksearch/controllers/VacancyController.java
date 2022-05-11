package com.example.worksearch.controllers;

import com.example.worksearch.controllers.schemes.*;
import com.example.worksearch.entities.City;
import com.example.worksearch.entities.Vacancy;
import com.example.worksearch.entities.VacancyResponse;
import com.example.worksearch.services.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/vacancy")
@AllArgsConstructor
public class VacancyController {

    private final VacancyService _vacancyService;
    private final CityService _cityService;
    private final EmployerService _employerService;

    private final VacancyResponseService _vacancyResponseService;
    private final ApplicantService _applicantService;


    @GetMapping("all")
    public List<ShortVacancySchema> getAll() {
        List<Vacancy> vacancies = _vacancyService.getAll();
        return vacancies.stream()
                .map(ShortVacancySchema::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("state/{vacancyState}")
    public List<ShortVacancySchema> getAll(@PathVariable String vacancyState) {
        List<Vacancy> vacancies = _vacancyService.getAllByVacancyState(vacancyState);
        return vacancies.stream()
                .map(ShortVacancySchema::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("id/{id}")
    public DetailedVacancySchema getById(@PathVariable long id) {
        Vacancy vacancy = _vacancyService.getById(id);
        return DetailedVacancySchema.fromEntity(vacancy);
    }

    @GetMapping("title/{title}")
    public DetailedVacancySchema getById(@PathVariable String title) {
        Vacancy vacancy = _vacancyService.getByTitle(title);
        return DetailedVacancySchema.fromEntity(vacancy);
    }

    @PostMapping("{id}/edit")
    public DetailedVacancySchema edit(UpdateVacancySchema newData, @PathVariable long id) {
        // TODO: инкапсулировть в сервис
        // TODO: ТРАНЗАКЦИЯ + блокировка FOR UPDATE на вакансию

        Vacancy vacancy = _vacancyService.getById(id);

        if (newData.getSalary() != null)
            vacancy.setSalary(newData.getSalary());

        if (newData.getTitle() != null)
            vacancy.setTitle(newData.getTitle());

        if (newData.getDescription() != null)
            vacancy.setDescription(newData.getDescription());

        if (newData.getCityId().isPresent()){
            long cityId = newData.getCityId().getAsLong();
            City newCity = _cityService.getById(cityId);
            vacancy.setCity(newCity);
        }

        Vacancy result = _vacancyService.save(vacancy);
        return DetailedVacancySchema.fromEntity(result);
    }

    @GetMapping("{id}/open")
    public DetailedVacancySchema openVacancy(@PathVariable long id){
        // TODO: ТРАНЗАКЦИЯ + блокировка FOR UPDATE на вакансию
        Vacancy vacancy = _vacancyService.getById(id);
        vacancy.setStateOpen();
        Vacancy result = _vacancyService.save(vacancy);
        return DetailedVacancySchema.fromEntity(result);
    }

    @PostMapping("add")
    public DetailedVacancySchema saveVacancy(@RequestBody CreateVacancySchema vacancySchema) {
        var vacancy = new Vacancy();
        if (vacancySchema.getSalary() != null)
            vacancy.setSalary(vacancySchema.getSalary());

        if (vacancySchema.getTitle() != null)
            vacancy.setTitle(vacancySchema.getTitle());

        if (vacancySchema.getDescription() != null)
            vacancy.setDescription(vacancySchema.getDescription());

        if (vacancySchema.getCityId().isPresent()) {
            long cityId = vacancySchema.getCityId().getAsLong();
            City newCity = _cityService.getById(cityId);
            vacancy.setCity(newCity);
        }
        if (vacancySchema.getEmployerId() > 0) {
            var employer = _employerService.getById(vacancySchema.getEmployerId());
            vacancy.setEmployer(employer);
        }
        vacancy = _vacancyService.save(vacancy);
        return DetailedVacancySchema.fromEntity(vacancy);
    }

    @PostMapping("response")
    public VacancyResponse createVacancyResponse(@RequestBody CreateVacancyResponseSchema schema) {
        var vacancyResponse = new VacancyResponse();
        var vacancy = _vacancyService.getById(schema.getVacancyId());
        var applicant = _applicantService.getById(schema.getApplicantId());
        vacancyResponse.setVacancy(vacancy);
        vacancyResponse.setApplicant(applicant);
        return _vacancyResponseService.save(vacancyResponse);
    }

    // TODO: assignVacancy
    // TODO: closeVacancy
}
