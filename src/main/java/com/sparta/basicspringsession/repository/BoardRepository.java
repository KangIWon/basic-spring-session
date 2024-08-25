package com.sparta.basicspringsession.repository;

import com.sparta.basicspringsession.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByCreatedAtBetweenOrderByModifiedAtDesc(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
