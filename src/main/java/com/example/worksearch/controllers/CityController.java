package com.example.worksearch.controllers;

import com.example.worksearch.DTOs.CityJSONParseDTO;
import com.example.worksearch.entities.City;
import com.example.worksearch.services.CityService;
import net.minidev.json.JSONArray;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("all")
    public List<String> getAllCityFromAPI() throws FileNotFoundException, ParseException {
        JSONParser parser = new JSONParser(new FileReader(System. getProperty("user.dir") + "/src/main/java/com/example/worksearch/datastabs/russian-cities.json"));
        ArrayList<Object> a = (ArrayList<Object>) parser.parse();
        List<CityJSONParseDTO> list = a.stream().map(CityJSONParseDTO::toModel).collect(Collectors.toList());
        return list.stream().map(f -> f.name).collect(Collectors.toList());
    }
}
