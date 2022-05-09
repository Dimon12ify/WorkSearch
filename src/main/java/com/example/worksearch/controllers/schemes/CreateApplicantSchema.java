package com.example.worksearch.controllers.schemes;

import lombok.Getter;

public class CreateApplicantSchema {
    @Getter
    private String email;

    @Getter
    private String firstName;

    @Getter
    private String secondName;

    @Getter
    private String patronymic;

    @Getter
    private String contact;

    @Getter
    private String contactType;

    @Getter
    private long cityId;
}
