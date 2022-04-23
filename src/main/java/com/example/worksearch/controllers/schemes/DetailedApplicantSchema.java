package com.example.worksearch.controllers.schemes;


import com.example.worksearch.entities.Applicant;
import lombok.Getter;


public class DetailedApplicantSchema {
    @Getter
    private final long id;

    @Getter
    private final String email;

    @Getter
    private final String firstName;

    @Getter
    private final String secondName;

    @Getter
    private final String patronymic;

    @Getter
    private final String contact;

    @Getter
    private final String contactType;

    @Getter
    private final CitySchema city;

    public DetailedApplicantSchema(
            long id,
            String email,
            String firstName,
            String secondName,
            String patronymic,
            String contact,
            String contactType,
            CitySchema city
    ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.contact = contact;
        this.contactType = contactType;
        this.city = city;
    }

    public static DetailedApplicantSchema fromEntity(Applicant applicant){
        return new DetailedApplicantSchema(
                applicant.getId(),
                applicant.getEmail(),
                applicant.getFirstName(),
                applicant.getSecondName(),
                applicant.getPatronymic(),
                applicant.getContact(),
                applicant.getContactType().name(),
                CitySchema.fromEntity(applicant.getCity())
        );
    }
}
