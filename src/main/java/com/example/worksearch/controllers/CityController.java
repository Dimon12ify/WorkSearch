package com.example.worksearch.controllers;

import com.example.worksearch.entities.City;
import com.example.worksearch.services.CityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/city")
@ResponseBody
public class CityController {
    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @PostMapping("add")
    public String add(@RequestBody City city) {
        service.save(city);
        return "Saved";
    }
}
