package com.example.worksearch.controllers;

import com.example.worksearch.entities.Employer;
import com.example.worksearch.services.EmployerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employer")
@ResponseBody
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

@ControllerAdvice
class CityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
