package com.felix.fonteneau.contentdrivenrest.model.filter;

import com.felix.fonteneau.contentdrivenrest.model.ApplicationData;
import com.felix.fonteneau.contentdrivenrest.model.Content;

public interface Filter {
    boolean filter(Content content, ApplicationData appData);
}
