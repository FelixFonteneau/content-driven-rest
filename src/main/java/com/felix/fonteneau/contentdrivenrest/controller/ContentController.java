package com.felix.fonteneau.contentdrivenrest.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.felix.fonteneau.contentdrivenrest.model.ApplicationDataString;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;
import com.felix.fonteneau.contentdrivenrest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ContentController {
    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/")
    public String index() {
        return "test";
    }

    @GetMapping(path = "/content", produces = "application/json")
    public Content getScreen(@RequestParam String id, @RequestParam(required = false) String applicationData) {
        return contentService.getScreen(id, new ApplicationDataString(applicationData))
                .orElse(null);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @PostMapping(path = "/addContent", consumes = "application/json")
    public ResponseEntity<Object> addContent(@RequestBody String contentableAsJson) {
        Contentable contentable;
        try {
            contentable = contentService.addContentAsJson(contentableAsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contentable.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
