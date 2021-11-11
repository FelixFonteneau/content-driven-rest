package com.felix.fonteneau.contentdrivenrest.service;

import com.felix.fonteneau.contentdrivenrest.dao.ContentableDAO;
import com.felix.fonteneau.contentdrivenrest.dao.FilterDAO;
import com.felix.fonteneau.contentdrivenrest.model.Alternative;
import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Condition;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {
    @Autowired
    private ContentableDAO contentableDAO;

    @Autowired
    private FilterDAO filterDAO;

    public Optional<Content> getScreen(String id, ApplicationData appData) {
        return contentableDAO.get(id)
                .map(contentable -> {
                    if (contentable instanceof Content) {
                        return (Content) contentable;
                    } else {
                        return resolveAlternative((Alternative) contentable, appData).orElse(null);
                    }
                });
    }

    private Optional<Content> resolveAlternative(Alternative alternative, ApplicationData appData) {
        return alternative.getPossibleAlternativesWithCondition()
                .parallelStream()
                .filter(pair -> filterCondition(pair.getLeft(), pair.getRight(), appData))
                .map(Pair::getLeft)
                .findAny();
    }

    private boolean filterCondition(Content content, Condition condition, ApplicationData applicationData) {
        if (condition.getFiltersName() != null) {
            return condition.getFiltersName().stream()
                    .map(filterDAO::get)
                    .allMatch(
                            optFilter ->
                                    optFilter
                                            .map(filter -> filter.filter(content, applicationData))
                                            .orElse(true) // if the filterName can't be resolve, we apply a true filter
                    );
        } else if (condition.getOR() != null) {
            return condition.getOR()
                    .stream()
                    .anyMatch(cond -> this.filterCondition(content, cond, applicationData));
        } else if (condition.getAND() != null) {
            return condition.getAND()
                    .stream()
                    .allMatch(cond -> this.filterCondition(content, cond, applicationData));
        }
        return true; // boundary case where there is no filter or condition, so it must be true.
    }
}
