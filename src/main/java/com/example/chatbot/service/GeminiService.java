package com.example.chatbot.service;


import com.example.chatbot.dto.gemini.Root;
import com.example.chatbot.dto.request.UserRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface GeminiService {
    Root processUserRequest(UserRequestDto userRequest);

    Root getLatestResponse();
}
