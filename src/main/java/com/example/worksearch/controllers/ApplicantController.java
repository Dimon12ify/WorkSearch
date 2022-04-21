package com.example.worksearch.controllers;

import com.example.worksearch.entities.Applicant;
import com.example.worksearch.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/applicant")
public class ApplicantController {

    @Autowired
    private ApplicantService service;

    @GetMapping("all")
    public List<Applicant> getAll() {
        return service.getAll();
    }

    @GetMapping("id/{id}")
    public Applicant getById(Long id) {
        return service.getById(id);
    }

    @GetMapping("fio/{FIO}")
    public Applicant getByFIO(@PathVariable String FIO) {
        return service.getByFIO(FIO);
    }

    @GetMapping("contact/{contact}")
    public Applicant getByContact(@PathVariable String contact) {
        return service.getByContact(contact);
    }

    @PostMapping("/add")
    public String add(@RequestBody Applicant applicant) {
        service.save(applicant);
        return "Ok";
    }

    @GetMapping("resume/{resumeId}")
    public Applicant getByResumeId(@PathVariable long resumeId) {
        return service.getByResumeId(resumeId);
    }
}
