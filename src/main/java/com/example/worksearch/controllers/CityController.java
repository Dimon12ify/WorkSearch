package com.example.worksearch.controllers;

import com.example.worksearch.controllers.schemes.CitySchema;
import com.example.worksearch.entities.City;
import com.example.worksearch.services.CityService;
import org.apache.coyote.Request;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.RequestMatchResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/city")
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

    @GetMapping("all")
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "false") Boolean names) {
        Object result;
        if (names)
            result = service.getAllNames();
        else
            result = service.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public CitySchema getByName(@PathVariable String name) {
        City city = service.getByName(name);
        return CitySchema.fromEntity(city);
    }

    @GetMapping("id/{id}")
    public CitySchema getByName(@PathVariable Long id) {
        City city = service.getById(id);
        return CitySchema.fromEntity(city);
    }

    @GetMapping("all/update")
    public String getUnsavedAndSave() throws IOException, ParseException {
        var unsaved = service.getUnsaved();
        unsaved.forEach(f -> service.save(new City(f)));
        return "Saved " + unsaved.size() + " entities";
    }
}
