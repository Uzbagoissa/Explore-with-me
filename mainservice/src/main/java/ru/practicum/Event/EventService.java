package ru.practicum.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(String text, List<Long> categories, boolean paid, String rangeStart, String rangeEnd,
                             boolean onlyAvailable, String sort, long from, long size);

    Event getEventById(long id);

    List<Event> getAllEventsByUserId(long userId, long from, long size);

    Event getEventByIdAndByUserId(long userId, long eventId);

    List<Event> getAllEventsAdmin(List<Long> usersIds, List<String> states, List<Long> categories, String rangeStart,
                                  String rangeEnd, long from, long size);

    Event saveEvent(long userId, EventDto eventDto);

    Event updateEvent(long userId, long eventId, EventDto eventDto);

    Event updateEventAdmin(long eventId, EventDto eventDto);
}