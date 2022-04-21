package com.example.worksearch.controllers;

import com.example.worksearch.entities.City;
import com.example.worksearch.services.CityService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("api/city")
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

    @GetMapping("updateAll")
    public String getUnsavedAndSave() throws FileNotFoundException, ParseException {
        var unsaved = service.getUnsaved();
        unsaved.forEach(f -> service.save(new City(f)));
        return "Saved " + unsaved.size() + " entities";
    }
}
