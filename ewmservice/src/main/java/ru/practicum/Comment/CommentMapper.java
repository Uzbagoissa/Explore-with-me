package ru.practicum.Comment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommentMapper {

    public static CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());
        commentDto.setUserId(comment.getUserId());
        commentDto.setEventId(comment.getEventId());
        commentDto.setCreated(comment.getCreated());
        return commentDto;
    }

    public static Comment toComment(CommentNewDto commentNewDto, long userId, long eventId, LocalDateTime created) {
        Comment comment = new Comment();
        comment.setComment(commentNewDto.getComment());
        comment.setUserId(userId);
        comment.setEventId(eventId);
        comment.setCreated(created);
        return comment;
    }

    /*public static List<CommentDto> toListUserDto(Iterable<Comment> users) {
        List<CommentDto> result = new ArrayList<>();
        for (Comment comment : users) {
            result.add(toUserDto(comment));
        }
        return result;
    }*/

}