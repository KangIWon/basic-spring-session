package com.sparta.basicspringsession.dto;

import lombok.Getter;

@Getter
public class BoardUpdateTitleResponseDto {
    private final Long id;
    private final String todo;
    private final String managerName;

    public BoardUpdateTitleResponseDto(Long id, String todo, String managerName) {
        this.id = id;
        this.todo = todo;
        this.managerName = managerName;
    }
}
