package com.example.worksearch.controllers.schemes;

import lombok.Getter;
import lombok.Setter;

public class CreateVacancyResponseSchema {

    @Getter @Setter
    private long vacancyId;

    @Getter @Setter
    private long applicantId;
}
