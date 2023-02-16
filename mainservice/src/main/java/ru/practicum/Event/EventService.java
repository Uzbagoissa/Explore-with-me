package ru.practicum.Event;

import java.util.List;

public interface EventService {
    List<FullEventDto> getAllEvents(String text, List<Long> categories, boolean paid, String rangeStart, String rangeEnd,
                                    boolean onlyAvailable, String sort, long from, long size);

    FullEventDto getEventById(long id);

    List<FullEventDto> getAllEventsByUserId(long userId, long from, long size);

    FullEventDto getEventByIdAndByUserId(long userId, long eventId);

    List<FullEventDto> getAllEventsAdmin(List<Long> userIds, List<String> states, List<Long> categories, String rangeStart,
                                         String rangeEnd, long from, long size);

    FullEventDto saveEvent(long userId, NewEventDto newEventDto);

    FullEventDto updateEvent(long userId, long eventId, NewEventDtoWithState newEventDtoWithState);

    FullEventDto updateEventAdmin(long eventId, NewEventDtoWithState newEventDtoWithState);
}