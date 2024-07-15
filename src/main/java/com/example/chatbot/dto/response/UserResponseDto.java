package com.example.chatbot.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String message;

    public UserResponseDto(String s) {
    }
}
