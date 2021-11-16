package com.felix.fonteneau.contentdrivenrest.util;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.felix.fonteneau.contentdrivenrest.model.Content;

import java.io.IOException;

/**
 * Problem handler for contentable, meaning if the "type" value on a content is unknown, it is
 * deserialized as a {@link Content}
 */
public class ContentableDeserializationProblemHandler extends DeserializationProblemHandler {
  @Override
  public JavaType handleUnknownTypeId(
      DeserializationContext ctxt,
      JavaType baseType,
      String subTypeId,
      TypeIdResolver idResolver,
      String failureMsg)
      throws IOException {
    return TypeFactory.defaultInstance().constructType(Content.class);
  }
}
