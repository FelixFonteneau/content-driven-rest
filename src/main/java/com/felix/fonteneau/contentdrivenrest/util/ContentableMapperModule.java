package com.felix.fonteneau.contentdrivenrest.util;


import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.felix.fonteneau.contentdrivenrest.model.Alternative;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentableMapperModule extends SimpleModule {
    private List<Class<?>> subTypes = new ArrayList<>();
    private List<NamedType> namedTypes = new ArrayList<>();
    private DeserializationProblemHandler deserializationProblemHandler;

    /** A Jackson module capable of deserializing standard Contentable classes */
    public ContentableMapperModule() {
        super();
        addInternalSubTypes();
        this.deserializationProblemHandler = new ContentableDeserializationProblemHandler();
    }


    public ContentableMapperModule(Class<? extends Contentable>... extraSubTypes) {
        super();
        addInternalSubTypes();
        subTypes.addAll(Arrays.asList(extraSubTypes));
        this.deserializationProblemHandler = new ContentableDeserializationProblemHandler();
    }


    public ContentableMapperModule(
            List<NamedType> namedTypes, List<Class<? extends Contentable>> extraSubTypes) {
        super();
        addInternalSubTypes();
        subTypes.addAll(extraSubTypes);
        this.namedTypes.addAll(namedTypes);
        this.deserializationProblemHandler = new ContentableDeserializationProblemHandler();
    }


    public ContentableMapperModule(
            List<NamedType> namedTypes,
            List<Class<? extends Contentable>> extraSubTypes,
            DeserializationProblemHandler customDeserializationProblemHandler) {
        super();
        addInternalSubTypes();
        subTypes.addAll(extraSubTypes);
        this.namedTypes.addAll(namedTypes);
        this.deserializationProblemHandler = customDeserializationProblemHandler;
    }

    private void addInternalSubTypes() {
        subTypes.addAll(
                List.of(Alternative.class));
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.registerSubtypes(subTypes);
        NamedType[] namedArray = new NamedType[] {};
        context.registerSubtypes(namedTypes.toArray(namedArray));
        context.addDeserializationProblemHandler(deserializationProblemHandler);
    }
}
