package ru.practicum.Requests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Request findRequestById(Long id);

    @Query(value = "SELECT r.* " +
            "FROM requests AS r " +
            "WHERE r.requester_id = ?1 ", nativeQuery = true)
    List<Request> findRequestsByUserId(long userId);

    @Query(value = "SELECT r.* " +
            "FROM requests AS r " +
            "WHERE r.requester_id = ?1 AND r.event_id = ?2 ", nativeQuery = true)
    List<Request> findRequestsByUserIdAndByEventId(long userId, long eventId);

    @Query(value = "SELECT r.* " +
            "FROM requests AS r " +
            "LEFT JOIN events AS e ON r.event_id = e.id " +
            "WHERE e.initiator_id = ?1 AND r.event_id = ?2 ", nativeQuery = true)
    List<Request> findAllByOwnerIdAndByEventId(long userId, long eventId);
}