package com.example.worksearch.controllers;

import com.example.worksearch.entities.Employer;
import com.example.worksearch.services.EmployerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employer")
public class EmployerController {
    private final EmployerService service;

    public EmployerController(EmployerService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<Employer> all(@RequestParam(required = false) int page, @RequestParam(required = false) int perPage) {
        return service.getAll(page, perPage);
    }

    @GetMapping("id/{id}")
    public Employer getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("add")
    public String add(@RequestBody Employer employer) {
        service.save(employer);
        return "Ok";
    }
}
