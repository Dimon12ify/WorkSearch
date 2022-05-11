package com.example.worksearch.controllers;

import com.example.worksearch.controllers.schemes.CreateApplicantSchema;
import com.example.worksearch.controllers.schemes.DetailedApplicantSchema;
import com.example.worksearch.controllers.schemes.ShortApplicantSchema;
import com.example.worksearch.entities.Applicant;
import com.example.worksearch.entities.City;
import com.example.worksearch.entities.enums.ContactType;
import com.example.worksearch.services.ApplicantService;
import com.example.worksearch.services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/applicant")
public class ApplicantController {

    private final ApplicantService applicantService;
    private final CityService cityService;

    public ApplicantController(ApplicantService service, CityService cityService) {
        this.applicantService = service;
        this.cityService = cityService;
    }

    @GetMapping("all")
    public List<ShortApplicantSchema> getAll() {
        List<Applicant> applicants = applicantService.getAll();
        return applicants.stream()
                .map(ShortApplicantSchema::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("id/{id}")
    public DetailedApplicantSchema getById(@PathVariable Long id) {
        Applicant applicant = applicantService.getById(id);
        return DetailedApplicantSchema.fromEntity(applicant);
    }

    @GetMapping("fullName/{fullName}")
    public DetailedApplicantSchema getByFullName(@PathVariable String fullName) {
        Applicant applicant = applicantService.getByFullName(fullName);
        return DetailedApplicantSchema.fromEntity(applicant);
    }

    @GetMapping("contact/{contact}")
    public DetailedApplicantSchema getByContact(@PathVariable String contact) {
        Applicant applicant = applicantService.getByContact(contact);
        return DetailedApplicantSchema.fromEntity(applicant);
    }

    @GetMapping("resume/{resumeId}")
    public DetailedApplicantSchema getByResumeId(@PathVariable long resumeId) {
        Applicant applicant = applicantService.getByResumeId(resumeId);
        return DetailedApplicantSchema.fromEntity(applicant);
    }

    @PostMapping("add")
    public DetailedApplicantSchema create(@RequestBody CreateApplicantSchema applicantToCreate) {
        Applicant applicant = new Applicant();
        applicant.setEmail(applicantToCreate.getEmail());
        applicant.setFirstName(applicantToCreate.getFirstName());
        applicant.setSecondName(applicantToCreate.getSecondName());
        applicant.setPatronymic(applicantToCreate.getPatronymic());
        applicant.setContact(applicantToCreate.getContact());

        ContactType contactType = ContactType.valueOf(applicantToCreate.getContactType());
        applicant.setContactType(contactType);

        City city = cityService.getById(applicantToCreate.getCityId());
        applicant.setCity(city);

        applicant = applicantService.save(applicant);
        return DetailedApplicantSchema.fromEntity(applicant);
    }
}
