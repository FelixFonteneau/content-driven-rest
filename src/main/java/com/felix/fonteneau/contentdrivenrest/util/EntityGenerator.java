package com.felix.fonteneau.contentdrivenrest.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.lang.reflect.Type;
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
      final Object[] array = jsonParser.readValueAs(Object[].class);
      return Pair.of(array[0], array[1]);
    }
  }
}
