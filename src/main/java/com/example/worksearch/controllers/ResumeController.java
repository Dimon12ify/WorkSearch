package com.example.worksearch.controllers;

import com.example.worksearch.controllers.schemes.ResumeSchema;
import com.example.worksearch.entities.Resume;
import com.example.worksearch.services.ResumeService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/resume")
@AllArgsConstructor
public class ResumeController {

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

    @PostMapping("/upload")
    public void acceptData(@RequestParam("file") MultipartFile file) throws Exception {
        WebClient client = WebClient.create();
        var uri = "https://storage.yandexcloud.net/worksearch/" + file.getOriginalFilename().replace(' ', '_');
        String response = client.put()
                .uri(new URI(uri))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(BodyInserters.fromMultipartData("file", file.getResource()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
