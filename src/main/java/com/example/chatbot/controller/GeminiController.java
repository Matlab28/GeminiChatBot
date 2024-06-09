package com.example.chatbot.controller;

import com.example.chatbot.dto.gemini.Root;
import com.example.chatbot.dto.request.UserRequestDto;
import com.example.chatbot.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class GeminiController {
    private final GeminiService geminiService;

    @PostMapping("/send")
    public Root sendMessageToGemini(@RequestBody UserRequestDto userRequest) {
        return geminiService.processUserRequest(userRequest);
    }

    @GetMapping("/response")
    public Root getLatestGeminiResponse() {
        return geminiService.getLatestResponse();
    }
}
