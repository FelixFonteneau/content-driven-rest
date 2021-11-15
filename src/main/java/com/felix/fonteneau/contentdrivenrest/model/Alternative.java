package com.felix.fonteneau.contentdrivenrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alternative implements Contentable {
    private String id;
    private List<Pair<Content, Condition>> possibleAlternativesWithCondition;
}
