package com.felix.fonteneau.contentdrivenrest.service;

import com.felix.fonteneau.contentdrivenrest.dao.FilterDAO;
import com.felix.fonteneau.contentdrivenrest.model.Alternative;
import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Condition;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FilteringEngine {
    private final FilterDAO filterDAO;

    @Autowired
    public FilteringEngine(FilterDAO filterDAO) {
        this.filterDAO = filterDAO;
    }

    public Optional<Content> resolveAlternative(Alternative alternative, ApplicationData appData) {
        return alternative.getPossibleAlternativesWithCondition()
                .parallelStream()
                .filter(pair -> filterCondition(pair.getLeft(), pair.getRight(), appData))
                .map(Pair::getLeft)
                .findAny();
    }

    public boolean filterCondition(Content content, Condition condition, ApplicationData applicationData) {
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
