package com.felix.fonteneau.contentdrivenrest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.felix.fonteneau.contentdrivenrest.model.Condition;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    module.addDeserializer(Pair.class, new PairDeserializer());
    module.addSerializer(Pair.class, new PairSerializer());
    mapper.registerModule(module);
    return mapper;
  }

  static class PairSerializer extends JsonSerializer<Pair> {

    @Override
    public void serialize(
        Pair pair, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
      HashMap<Content, Condition> mapPair = new HashMap();

      jsonGenerator.writeObject(new HashMap<>());
      jsonGenerator.writeStartArray(2);
      jsonGenerator.writeObject(pair.getLeft());
      jsonGenerator.writeObject(pair.getRight());
      jsonGenerator.writeEndArray();
    }
  }

  static class PairDeserializer extends JsonDeserializer<Pair> {

    @Override
    public Pair deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
      TypeReference<HashMap<Content, Condition>> typeRef = new TypeReference<HashMap<Content, Condition>>() {};

      final Map<Content, Condition> map = jsonParser.readValueAs(typeRef);
      Map.Entry<Content, Condition> entry = map.entrySet().iterator().next();
      return Pair.of(entry.getKey(), entry.getValue());
    }
  }
}
