package com.example.worksearch.services;

import com.example.worksearch.entities.Resume;
import com.example.worksearch.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository repository;

    @Autowired
    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }

    public List<Resume> getAll() {
        return repository.findAll();
    }

    public Resume getById(long id) {
        return repository.getById(id);
    }

    public Resume getByApplicantId(long applicantId) {
        return repository.getByApplicantId(applicantId);
    }
}
