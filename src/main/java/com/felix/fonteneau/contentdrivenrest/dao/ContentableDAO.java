package com.felix.fonteneau.contentdrivenrest.dao;

import com.felix.fonteneau.contentdrivenrest.model.Contentable;

import java.util.LinkedHashMap;

public class ContentableDAO {
    private final LinkedHashMap<String, Contentable> contentableById;

    public ContentableDAO() {
        this.contentableById = new LinkedHashMap<>();
    }

    public Contentable get(String id) {
        return contentableById.get(id);
    }
}
