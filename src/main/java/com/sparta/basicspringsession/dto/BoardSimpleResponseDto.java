package com.sparta.basicspringsession.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardSimpleResponseDto {
    private final Long id;
    private final String todo;
    private final String managerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public BoardSimpleResponseDto(Long id, String todo, String managerName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.todo = todo;
        this.managerName = managerName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
