package com.felix.fonteneau.contentdrivenrest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.felix.fonteneau.contentdrivenrest.dao.ContentableDAO;
import com.felix.fonteneau.contentdrivenrest.model.Alternative;
import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;
import com.felix.fonteneau.contentdrivenrest.util.EntityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {
    private final ContentableDAO contentableDAO;
    private final FilteringEngine filteringEngine;


    @Autowired
    public ContentService(ContentableDAO contentableDAO, FilteringEngine filteringEngine) {
        this.contentableDAO = contentableDAO;
        this.filteringEngine = filteringEngine;
    }

    /**
     * Get a screen
     * @param id id
     * @param appData filtering data
     * @return the screen if it exists and resolve the alternative if there exist some.
     */
    public Optional<Content> getScreen(String id, ApplicationData appData) {
        return contentableDAO.get(id)
                .map(contentable -> {
                    if (contentable instanceof Content) {
                        return (Content) contentable;
                    } else {
                        return filteringEngine.resolveAlternative((Alternative) contentable, appData).orElse(null);
                    }
                });
    }

    public Contentable addContentAsJson(String contentAsJson) throws JsonProcessingException {
        Contentable contentable = EntityGenerator.generateContentFromJson(contentAsJson);
        contentableDAO.addOrReplace(contentable);
        return contentable;
    }


}
