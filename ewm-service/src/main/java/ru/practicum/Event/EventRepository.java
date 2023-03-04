package ru.practicum.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById(Long id);

    @Query(value = "SELECT e.* " +
            "FROM events AS e " +
            "WHERE e.initiator_id = ?1 ", nativeQuery = true)
    List<Event> findEventsByUserId(long userId);

    @Query(value = "SELECT e.* " +
            "FROM events AS e " +
            "WHERE e.id = ?1 AND e.initiator_id = ?2", nativeQuery = true)
    Event findEventByIdAndByUserId(long eventId, long userId);

    @Query(value = "SELECT e.* " +
            "FROM events AS e " +
            "WHERE e.event_date > ?1 AND e.event_date < ?2 AND e.state = ?3 AND e.category_id = ?4 " +
            "AND e.initiator_id = ?5 ", nativeQuery = true)
    List<Event> findEventsByParameters(LocalDateTime rangeStart, LocalDateTime rangeEnd, String state, Long category, Long userId);

    @Query(value = "SELECT e.* " +
            "FROM events AS e " +
            "WHERE e.event_date > ?1 AND e.event_date < ?2 AND e.category_id = ?3 " +
            "AND e.paid = ?4 AND e.state = ?5 ", nativeQuery = true)
    List<Event> findEventsPublishedByParameters(LocalDateTime rangeStart, LocalDateTime rangeEnd, Long category,
                                                boolean paid, String state);

}