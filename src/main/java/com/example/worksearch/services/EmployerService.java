package com.example.worksearch.services;

import com.example.worksearch.entities.Employer;
import com.example.worksearch.repositories.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {
    private final EmployerRepository repository;

    @Autowired
    public EmployerService(EmployerRepository repository) {
        this.repository = repository;
    }

    public void save(Employer employer) throws IllegalArgumentException {
        if (employer.getCompanyName().isEmpty())
            throw new IllegalArgumentException("Company name shouldn't be empty");
        if (repository.findByCompanyName(employer.getCompanyName()) != null)
            throw new IllegalArgumentException("Employer " + employer.getCompanyName() + " is already exists");
        repository.save(employer);
    }

    public List<Employer> findAll(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = Math.max(perPage, 10);
        // return repository.findAll().subList(page * perPage, (page + 1) * perPage);
        return repository.findAll();
    }
}
