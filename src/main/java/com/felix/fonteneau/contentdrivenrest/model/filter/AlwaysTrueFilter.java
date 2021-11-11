package com.felix.fonteneau.contentdrivenrest.model.filter;

import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Content;

public class AlwaysTrueFilter implements Filter {
    @Override
    public boolean filter(Content content, ApplicationData appData) {
        return true;
    }
}
