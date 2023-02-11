package ru.practicum.stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {
    @Query(value = "select count(e.app) " +
            "from endpoint_hit as e " +
            "where e.time_stamp >= ?1 and e.time_stamp <= ?2 and e.uri = ?3 ", nativeQuery = true)
    List<String> findEndpointHitByUri(LocalDateTime start, LocalDateTime end, String uri);

    @Query(value = "select e.app " +
            "from endpoint_hit as e " +
            "where e.time_stamp >= ?1 and e.time_stamp <= ?2 and e.uri = ?3 " +
            "group by e.ip, e.id ", nativeQuery = true)
    List<String> findAppByUriByUniqIp(LocalDateTime start, LocalDateTime end, String uri);

}