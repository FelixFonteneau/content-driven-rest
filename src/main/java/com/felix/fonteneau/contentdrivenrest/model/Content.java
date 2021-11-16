package com.felix.fonteneau.contentdrivenrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Contentable {
    private String id;
    private String type;
    private List<Content> nestedContent;
    private Link link;
    private String text;
    private String media;
    private Map<String, List<String>> metadata;
}
