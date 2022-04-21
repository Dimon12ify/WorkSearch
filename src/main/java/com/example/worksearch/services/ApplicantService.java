package com.example.worksearch.services;

import com.example.worksearch.entities.Applicant;
import com.example.worksearch.repositories.ApplicantRepository;
import com.example.worksearch.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {
    private final ApplicantRepository repository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    public ApplicantService(ApplicantRepository repository) {
        this.repository = repository;
    }

    public List<Applicant> getAll() {
        return repository.findAll();
    }

    public Applicant getById(long id) {
        return repository.getById(id);
    }

    public Applicant getByContact(String contact) {
        return repository.getByContact(contact);
    }

    public Applicant getByFIO(String FIO) {
        var data = FIO.split(" ");
        if (data.length > 3 || data.length < 2 || data[0].isBlank() || data[1].isBlank())
            throw new IllegalArgumentException("Wrong FIO");
        return repository.getByFirstNameAndSecondNameAndPatronymic(data[0], data[1], data[2]);
    }

    public void save(Applicant applicant) {
        repository.save(applicant);
    }

    public Applicant getByResumeId(long resumeId) {
        var resume = resumeRepository.getById(resumeId);
        return resume.getApplicant();
    }
}
