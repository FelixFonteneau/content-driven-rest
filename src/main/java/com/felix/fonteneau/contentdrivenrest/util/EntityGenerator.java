package com.felix.fonteneau.contentdrivenrest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;

import java.util.List;


public class EntityGenerator {
  private EntityGenerator() {
    throw new IllegalStateException("Utility class");
  }

  public static List<Contentable> generateContentsFromJson(String jsonContents)
      throws JsonProcessingException {
    ObjectMapper mapper = generateContentObjectMapper();
    return mapper.readValue(jsonContents, new TypeReference<List<Contentable>>() {});
  }


  public static Contentable generateContentFromJson(String jsonContent) throws JsonProcessingException {
    ObjectMapper mapper = generateContentObjectMapper();
    return mapper.readValue(jsonContent, new TypeReference<Contentable>() {});
  }



  public static ObjectMapper generateContentObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    ContentableMapperModule module = new ContentableMapperModule();
    mapper.registerModule(module);
    return mapper;
  }
}
