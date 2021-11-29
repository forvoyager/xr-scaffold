package com.xr.base.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

/**
 * json operation.
 */
public class JsonUtils {

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // 空对象( new Object() )序列化不报错
  }

  public static String toJson(Object object) throws Exception {
    if (object == null) {
      return null;
    }
    return mapper.writeValueAsString(object);
  }

  public static <T> T toObject(String json, Class clazz) throws Exception {
    if (StringUtils.isEmpty(json)) {
      return null;
    }
    return (T) mapper.readValue(json, clazz);
  }

  public static <T> T toObject(String json, TypeReference<T> typeReference) throws Exception {
    if (StringUtils.isEmpty(json)) {
      return null;
    }
    return mapper.readValue(json, typeReference);
  }

  public static JsonNode toObject(String json) throws IOException {
    return mapper.reader().readTree(json);
  }
}
