package com.flywithus.integration;

import com.flywithus.Application;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class IntegrationTest {

    private final Gson gson = createGson();

    @Autowired
    private MockMvc mvc;

    private Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
    }

    Gson gson() {
        return gson;
    }

    String uuid() {
        return UUID.randomUUID().toString();
    }

    ResultActions postJson(String path, Object payload) throws Exception {
        return sendJson(post(path), payload);
    }

    ResultActions putJson(String path, Object payload) throws Exception {
        return sendJson(put(path), payload);
    }

    ResultActions deleteJson(String path, Object payload) throws Exception {
        return sendJson(delete(path), payload);
    }

    ResultActions sendJson(MockHttpServletRequestBuilder method, Object payload) throws Exception {
        return mvc.perform(method.content(gson().toJson(payload)).contentType(APPLICATION_JSON).accept(APPLICATION_JSON));
    }

    static class LocalDateSerializer implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.toString());
        }

    }

    static class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString());
        }

    }

}
