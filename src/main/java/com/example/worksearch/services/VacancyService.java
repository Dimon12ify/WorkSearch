package com.example.worksearch.services;

import com.example.worksearch.entities.Vacancy;
import com.example.worksearch.entities.enums.VacancyState;
import com.example.worksearch.repositories.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class VacancyService {
    private final VacancyRepository repository;

    public VacancyService(VacancyRepository repository) {
        this.repository = repository;
    }

    public List<Vacancy> getAll() {
        return repository.findAll();
    }

    public Vacancy getById(long id) {
        return repository.getById(id);
    }

    public Vacancy getByTitle(String title) {
        return repository.getByTitle(title);
    }

    public List<Vacancy> getAllByVacancyState(String state) {
        switch (state.toLowerCase(Locale.ROOT)) {
            case "draft":
                return repository.getAllByState(VacancyState.DRAFT);
            case "open":
                return repository.getAllByState(VacancyState.OPEN);
            case "assigned":
                return repository.getAllByState(VacancyState.ASSIGNED);
            case "closed":
                return repository.getAllByState(VacancyState.CLOSED);
        }
        return null;
    }

    public Vacancy save(Vacancy vacancy){
        return repository.save(vacancy);
    }
}
