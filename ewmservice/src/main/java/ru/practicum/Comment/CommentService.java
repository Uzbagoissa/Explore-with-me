package ru.practicum.Comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.exceptions.IncorrectParameterException;

import javax.validation.Valid;
import java.util.List;

public interface CommentService {
    List<CommentDto> getAllCommentsByUserId(long from, long size);

    CommentDto getCommentById(long commentId);

    CommentDto saveComment(CommentNewDto commentNewDto, long userId);

    CommentDto updateComment(CommentNewDto commentNewDto, long commentId);

    void removeComment(long commentId);
}