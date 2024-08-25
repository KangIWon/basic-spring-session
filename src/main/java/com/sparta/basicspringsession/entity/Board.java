package com.sparta.basicspringsession.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String todo;
    private String managerName;
    private String password;

    public Board(String todo, String managerName, String password) {
        this.todo = todo;
        this.managerName = managerName;
        this.password = password;
    }

    public void update(String todo, String managerName) {
        this.todo = todo;
        this.managerName = managerName;
    }
}
