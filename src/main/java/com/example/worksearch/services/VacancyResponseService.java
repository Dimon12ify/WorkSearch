package com.example.worksearch.services;

import com.example.worksearch.entities.VacancyResponse;
import com.example.worksearch.repositories.VacancyResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VacancyResponseService {
    private final VacancyResponseRepository repository;

    public VacancyResponse save(VacancyResponse vacancyResponse) {
        return repository.save(vacancyResponse);
    }
}
