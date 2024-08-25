package com.sparta.basicspringsession.dto;

import lombok.Getter;

@Getter
public class BoardSaveRequestDto {
    private String todo;
    private String managerName;
    private String password;
}
