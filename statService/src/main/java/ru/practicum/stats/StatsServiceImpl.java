package ru.practicum.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;

    @Override
    public List<ViewStats> getStats(String start, String end, List<String> uris, boolean unique) {
        List<ViewStats> viewStatsList = new ArrayList<>();
        for (String uri : uris) {
            if (unique == true) {

            } else {
                List<String> apps = repository.findEndpointHitByUri(
                        StatsMapper.stringToLocalDateTime(start),
                        StatsMapper.stringToLocalDateTime(end),
                        uri);
                for (String app : apps) {
                    viewStatsList.add(new ViewStats(app, uri, (long) apps.size()));
                }
            }
        }
        return viewStatsList;
    }

    @Transactional
    @Override
    public EndpointHitDto saveHit(EndpointHitDto endpointHitDto) {
        return StatsMapper.toEndpointHitDto(repository.save(StatsMapper.toEndpointHit(endpointHitDto)));
    }
}