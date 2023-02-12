package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.mappers.StatsMapper;
import ru.practicum.mappers.ViewStatsRowMapper;
import ru.practicum.model.ViewStats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, boolean unique) {
        List<ViewStats> viewStatsList = new ArrayList<>();
        for (String uri : uris) {
            String sqlQuery;
            if (unique) {
                sqlQuery = "SELECT app, uri, COUNT(DISTINCT ip) AS hits FROM endpoint_hit " +
                        "WHERE uri = ? AND time_stamp > ? AND time_stamp < ? " +
                        "GROUP BY app, uri ";
            } else {
                sqlQuery = "SELECT app, uri, COUNT(ip) AS hits FROM endpoint_hit " +
                        "WHERE uri = ? AND time_stamp > ? AND time_stamp < ? " +
                        "GROUP BY app, uri ";
            }
            List<ViewStats> viewStatsListByOneUri = jdbcTemplate.query(sqlQuery, new ViewStatsRowMapper(), uri,
                    StatsMapper.stringToLocalDateTime(start), StatsMapper.stringToLocalDateTime(end));
            viewStatsList.addAll(viewStatsListByOneUri);
        }
        viewStatsList.sort(Comparator.comparing(ViewStats::getHits).reversed());
        return viewStatsList;
    }

    @Transactional
    @Override
    public EndpointHitDto saveHit(EndpointHitDto endpointHitDto) {
        return StatsMapper.toEndpointHitDto(repository.save(StatsMapper.toEndpointHit(endpointHitDto)));
    }
}