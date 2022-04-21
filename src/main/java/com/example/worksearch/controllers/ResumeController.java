package com.example.worksearch.controllers;

import com.example.worksearch.entities.Resume;
import com.example.worksearch.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/resume")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @GetMapping("all")
    public List<Resume> getAll() {
        return service.getAll();
    }

    @GetMapping("id/{id}")
    public Resume getById(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping("applicant/{applicantId}")
    public Resume getByApplicantId(@PathVariable long applicantId) {
        return service.getByApplicantId(applicantId);
    }
}
