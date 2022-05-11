package com.example.worksearch.controllers;

import com.example.worksearch.controllers.schemes.ResumeSchema;
import com.example.worksearch.entities.Resume;
import com.example.worksearch.services.ResumeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/resume")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @GetMapping("all")
    public List<ResumeSchema> getAll() {
        List<Resume> resumes = service.getAll();
        return resumes.stream()
                .map(ResumeSchema::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResumeSchema getById(@PathVariable long id){
        Resume resume = service.getById(id);
        return ResumeSchema.fromEntity(resume);
    }

    @GetMapping("{id}/download")
    public void downloadById(@PathVariable long id, HttpServletResponse response) {
        Resume resume = service.getById(id);

        try {
            response.setContentType(resume.getContentType());
            response.setHeader(
                    "Content-Disposition",
                    String.format("attachment; filename=%s", resume.getContentType())
            );

            InputStream contentStream = new ByteArrayInputStream(resume.getContent());
            IOUtils.copy(contentStream, response.getOutputStream());
            response.flushBuffer();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
