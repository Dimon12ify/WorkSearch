package com.example.worksearch.controllers.schemes;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.OptionalLong;

public class CreateVacancySchema {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal salary;

    @Getter
    @Setter
    private OptionalLong cityId;

    @Getter @Setter
    private long employerId;
}
