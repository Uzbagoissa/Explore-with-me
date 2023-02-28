package ru.practicum.Comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.Event.EventFullDto;
import ru.practicum.Event.EventNewDtoForUpdate;
import ru.practicum.exceptions.IncorrectParameterException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllCommentsByUserId(@RequestParam(value = "from", defaultValue = "0") long from,
                                                   @RequestParam(value = "size", defaultValue = "10") long size) {
        if (from < 0) {
            log.info("Неверный параметр from: {}, from должен быть больше или равен 0 ", from);
            throw new IncorrectParameterException("Неверный параметр from: {}, from должен быть больше или равен 0 " + from);
        }
        if (size <= 0) {
            log.info("Неверный параметр size: {}, size должен быть больше или равен 0 ", size);
            throw new IncorrectParameterException("Неверный параметр size: {}, size должен быть больше или равен 0 " + size);
        }
        log.info("Комментарии найдены");
        return commentService.getAllCommentsByUserId(from, size);
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable long commentId) {
        log.info("Комментарий найден");
        return commentService.getCommentById(commentId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto saveComment(@PathVariable long userId,
                                  @Valid @RequestBody CommentNewDto commentNewDto) {
        log.info("Комментарий сохранен");
        return commentService.saveComment(commentNewDto, userId);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@Valid @RequestBody CommentNewDto commentNewDto,
                                    @PathVariable long commentId) {
        log.info("Комментарий обновлен");
        return commentService.updateComment(commentNewDto, commentId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable long commentId) {
        log.info("Комментарий удален");
        commentService.removeComment(commentId);
    }
}
