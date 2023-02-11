package ru.practicum.stats;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StatsMapper {

    public static EndpointHit toEndpointHit(EndpointHitDto endpointHitDto) {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setApp(endpointHitDto.getApp());
        endpointHit.setUri(endpointHitDto.getUri());
        endpointHit.setIp(endpointHitDto.getIp());
        endpointHit.setTimestamp(stringToLocalDateTime(endpointHitDto.getTimestamp()));
        return endpointHit;
    }

    public static EndpointHitDto toEndpointHitDto(EndpointHit endpointHit) {
        EndpointHitDto endpointHitDto = new EndpointHitDto();
        endpointHitDto.setId(endpointHit.getId());
        endpointHitDto.setApp(endpointHit.getApp());
        endpointHitDto.setUri(endpointHit.getUri());
        endpointHitDto.setIp(endpointHit.getIp());
        endpointHitDto.setTimestamp(localDateTimeToString(endpointHit.getTimestamp()));
        return endpointHitDto;
    }

    public static ViewStats toViewStats(EndpointHit endpointHit, Long hits) {
        ViewStats viewStats = new ViewStats();
        viewStats.setApp(endpointHit.getApp());
        viewStats.setUri(endpointHit.getUri());
        viewStats.setHits(hits);
        return viewStats;
    }

    public static List<ViewStats> toViewStatsList(List<EndpointHit> endpointHits, Long hits) {
        List<ViewStats> result = new ArrayList<>();
        for (EndpointHit endpointHit : endpointHits) {
            result.add(toViewStats(endpointHit, hits));
        }
        return result;
    }

    public static LocalDateTime stringToLocalDateTime(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
        return localDateTime;
    }

    public static String localDateTimeToString(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localDateTime = timestamp.format(formatter);;
        return localDateTime;
    }

}