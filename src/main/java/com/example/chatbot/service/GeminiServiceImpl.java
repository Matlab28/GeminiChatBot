package com.example.chatbot.service;

import com.example.chatbot.client.GeminiApiClient;
import com.example.chatbot.dto.gemini.Root;
import com.example.chatbot.dto.request.UserRequestDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeminiServiceImpl implements GeminiService {
    private final GeminiApiClient client;
    private final String key = "AIzaSyDh7JeIjfGp1MSe1NMCV_0s0l5dvaIJtig";
    private Root latestResponse;

    @Autowired
    public GeminiServiceImpl(GeminiApiClient client) {
        this.client = client;
    }

    @Override
    public Root processUserRequest(UserRequestDto userRequest) {
        String messageText = userRequest.getMessage();
        latestResponse = getUpdates(messageText);
        return latestResponse;
    }

    @Override
    public Root getLatestResponse() {
        return latestResponse;
    }

    private Root getUpdates(String messageText) {
        try {
            JsonObject json = new JsonObject();
            JsonArray contentsArray = new JsonArray();
            JsonObject contentsObject = new JsonObject();
            JsonArray partsArray = new JsonArray();
            JsonObject partsObject = new JsonObject();

            partsObject.add("text", new JsonPrimitive(messageText));
            partsArray.add(partsObject);
            contentsObject.add("parts", partsArray);
            contentsArray.add(contentsObject);
            json.add("contents", contentsArray);

            String content = json.toString();
            return client.getData(key, content);
        } catch (Exception e) {
            log.error("Error while getting updates from Gemini API: ", e);
            throw e;
        }
    }
}
