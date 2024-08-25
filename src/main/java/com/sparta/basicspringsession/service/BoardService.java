package com.sparta.basicspringsession.service;

import com.sparta.basicspringsession.dto.*;
import com.sparta.basicspringsession.entity.Board;
import com.sparta.basicspringsession.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveResponseDto saveBoard(BoardSaveRequestDto requestDto) {
        Board newBoard = new Board(requestDto.getTodo(), requestDto.getManagerName(), requestDto.getPassword());
        Board saveBoard = boardRepository.save(newBoard);
        return new BoardSaveResponseDto(saveBoard.getId(),
                saveBoard.getTodo(),
                saveBoard.getManagerName(), saveBoard.getCreatedAt(), saveBoard.getModifiedAt());
    }

    public List<BoardSimpleResponseDto> getBoards(String date) {
        LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

        List<Board> boardList = boardRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime);

        List<BoardSimpleResponseDto> dtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardSimpleResponseDto dto = new BoardSimpleResponseDto(board.getId(),
                    board.getTodo(), board.getManagerName(), board.getCreatedAt(),
                    board.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public BoardDetailResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("멤버가 없습니다."));
        return new BoardDetailResponseDto(board.getId(), board.getTodo(), board.getManagerName(),
                board.getCreatedAt(), board.getModifiedAt());
    }

    @Transactional
    public BoardUpdateTitleResponseDto updateBoard(Long boardId, BoardUpdateTitleResponseDto requestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("멤버가 없습니다."));
        board.update(requestDto.getTodo(), requestDto.getManagerName());
        return  new BoardUpdateTitleResponseDto(
                board.getId(),
                board.getTodo(),
                board.getManagerName()
        );
    }

    @Transactional
    public void deleteBoard(Long boardId, BoardDetailRequestDto requestDto) {
        String password = requestDto.getPassword();
        if (password == null) {
            throw new NullPointerException("비밀번호가 없습니다.");
        }
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("보드가 없습니다."));
        if (!password.equals(board.getPassword())) {
            throw new NullPointerException("비밀번호가 틀렸습니다.");
        }
        boardRepository.deleteById(boardId);
    }
}
