package com.example.worksearch.controllers.schemes;

import com.example.worksearch.entities.Applicant;
import lombok.Getter;


public class ShortApplicantSchema {
    @Getter
    private final long id;

    @Getter
    private final String email;

    @Getter
    private final String fullName;

    @Getter
    private final String cityName;

    private ShortApplicantSchema(
            long id,
            String email,
            String fullName,
            String cityName
    ){
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.cityName = cityName;
    }

    public static ShortApplicantSchema fromEntity(Applicant applicant){
        return new ShortApplicantSchema(
                applicant.getId(),
                applicant.getEmail(),
                applicant.getSecondName() + ' ' + applicant.getFirstName() + ' ' + applicant.getPatronymic(),
                applicant.getCity().getName()
        );
    }
}
