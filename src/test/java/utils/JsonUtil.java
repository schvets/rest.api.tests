package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper objectMapper;

    private JsonUtil() {
    }

    public static <T> T fromJson(final String str, final Class<T> returnType) {
        try {
            return mapper().readValue(str, returnType);
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static ObjectMapper mapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
                    .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                    .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .registerModule(new JavaTimeModule());
        }
        return objectMapper;
    }

}
