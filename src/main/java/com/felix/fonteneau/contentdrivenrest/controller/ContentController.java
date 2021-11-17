package com.felix.fonteneau.contentdrivenrest.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.felix.fonteneau.contentdrivenrest.model.ApplicationDataString;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;
import com.felix.fonteneau.contentdrivenrest.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ContentController {
    private final ContentService contentService;
    private Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/")
    public String index() {
        logger.info("Request on the index.");
        return "test";
    }

    @GetMapping(path = "/content", produces = "application/json")
    public ResponseEntity<Content> getScreen(@RequestParam String id, @RequestParam(required = false) String applicationData) {
        logger.info("Get screen request with id: {} and applicationData: {}", id, applicationData);

        return contentService.getScreen(id, new ApplicationDataString(applicationData))
                .map(content -> {
                    logger.info("Content found: {}", content);
                    return ResponseEntity.ok(content);
                })
                .orElseGet(() -> {
                    logger.info("Content not found.");
                    return ResponseEntity.notFound().build();
                });
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @PostMapping(path = "/addContent", consumes = "application/json")
    public ResponseEntity<Object> addContent(@RequestBody String contentableAsJson) {
        Contentable contentable;
        try {
            contentable = contentService.addContentAsJson(contentableAsJson);
        } catch (JsonProcessingException e) {
            logger.error("Error while adding content:", e);
            return ResponseEntity.badRequest().build();
        }
        logger.info("Contentable created: {}", contentable);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contentable.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
