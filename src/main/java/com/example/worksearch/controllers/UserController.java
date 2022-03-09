package com.example.worksearch.controllers;

import com.example.worksearch.entities.Employer;
import com.example.worksearch.repositories.EmployerRepository;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class UserController {
    private final EmployerRepository _repository;

    public UserController(EmployerRepository repository) {
        _repository = repository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/all")
    public List<Employer> all() {
        return _repository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Employer employer) {
        if (employer == null) {
            return new ResponseEntity<>("empty", HttpStatus.BAD_REQUEST);
        }
        _repository.save(employer);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
