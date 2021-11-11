package com.felix.fonteneau.contentdrivenrest.controller;

import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping(path = "/", produces = "application/json")
    public Content getScreen(@RequestParam String id, @RequestParam ApplicationData applicationData) {
        return contentService.getScreen(id, applicationData)
                .orElse(null);
    }

}
