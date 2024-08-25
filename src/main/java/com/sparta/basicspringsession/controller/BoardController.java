package com.sparta.basicspringsession.controller;

import com.sparta.basicspringsession.dto.*;
import com.sparta.basicspringsession.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<BoardSaveResponseDto> saveBoard(@RequestBody BoardSaveRequestDto requestDto) {
        return ResponseEntity.ok(boardService.saveBoard(requestDto));
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardSimpleResponseDto>> getBoards(@RequestParam String date) {
        return ResponseEntity.ok(boardService.getBoards(date));
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardUpdateTitleResponseDto> updateBoard(
            @PathVariable Long boardId, @RequestBody BoardUpdateTitleResponseDto requestDto) {
        return ResponseEntity.ok(boardService.updateBoard(boardId, requestDto));
    }

    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId, @RequestBody BoardDetailRequestDto requestDto) {
        boardService.deleteBoard(boardId, requestDto);
    }
}
