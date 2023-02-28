package ru.practicum.Comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.Category.CategoryMapper;
import ru.practicum.User.UserMapper;
import ru.practicum.exceptions.ConflictException;
import ru.practicum.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Override
    public List<CommentDto> getAllCommentsByUserId(long from, long size) {
        return repository.findAll().stream()
                .skip(from)
                .limit(size)
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long commentId) {
        commentValid(commentId);
        return CommentMapper.toCommentDto(repository.getById(commentId));
    }

    @Override
    public CommentDto saveComment(CommentNewDto commentNewDto, long userId) {
        return CommentMapper.toCommentDto(repository.save(CommentMapper.toComment(commentNewDto, userId, 8,
                LocalDateTime.now())));
    }

    @Override
    public CommentDto updateComment(CommentNewDto commentNewDto, long commentId) {
        return null;
    }

    @Override
    public void removeComment(long commentId) {

    }

    private void commentValid(long commentId) {
        if (repository.findCommentById(commentId) == null) {
            log.error("Комментарий c {} не найден", commentId);
            throw new NotFoundException("Комментарий не найден");
        }
    }
}