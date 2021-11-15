package com.felix.fonteneau.contentdrivenrest.dao;

import com.felix.fonteneau.contentdrivenrest.model.filter.AlwaysFalseFilter;
import com.felix.fonteneau.contentdrivenrest.model.filter.AlwaysTrueFilter;
import com.felix.fonteneau.contentdrivenrest.model.filter.Filter;
import com.felix.fonteneau.contentdrivenrest.model.filter.RandomFilter;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class FilterDAO {
    private final Map<String, Filter> filtersByName;

    public FilterDAO() {
        this.filtersByName = filterInit();
    }

    public Optional<Filter> get(String filterName) {
        return Optional.ofNullable(filtersByName.get(filterName));
    }

    /**
     * @return all the filters needed for the tests with their named mapped.
     */
    private static Map<String, Filter> filterInit() {
        return Map.of(
                "alwaysTrue", new AlwaysTrueFilter(),
                "alwaysFalse", new AlwaysFalseFilter(),
                "rand", new RandomFilter(),
                "rand70", new RandomFilter(0.70),
                "rand30", new RandomFilter(0.30)
        );
    }
}
