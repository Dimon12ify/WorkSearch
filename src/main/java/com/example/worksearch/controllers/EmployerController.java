package com.example.worksearch.controllers;

import com.example.worksearch.entities.Employer;
import com.example.worksearch.services.EmployerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employer")
public class EmployerController {
    private final EmployerService service;

    public EmployerController(EmployerService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<Employer> all(@RequestParam(required = false) int page, @RequestParam(required = false) int perPage) {
        return service.findAll(page, perPage);
    }

    @PostMapping("add")
    public String add(@RequestBody Employer employer) {
        service.save(employer);
        return "Ok";
    }
}
