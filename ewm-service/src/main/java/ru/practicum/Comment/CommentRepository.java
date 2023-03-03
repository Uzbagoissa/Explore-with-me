package ru.practicum.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentById(Long id);

    @Query(value = "SELECT c.* " +
            "FROM comments AS c " +
            "WHERE c.user_id = ?1 ", nativeQuery = true)
    List<Comment> findCommentsByUserId(long userId);

    @Query(value = "SELECT c.* " +
            "FROM comments AS c " +
            "WHERE c.event_id = ?1 ", nativeQuery = true)
    List<Comment> findCommentsByEventId(long eventId);

}