package ru.practicum.Event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.Category.*;
import ru.practicum.exceptions.ConflictException;
import ru.practicum.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Event> getAllEvents(String text, List<Long> categories, boolean paid, String rangeStart,
                                    String rangeEnd, boolean onlyAvailable, String sort, long from, long size) {
        return null;
    }

    @Override
    public Event getEventById(long id) {
        return null;
    }

    @Override
    public List<Event> getAllEventsByUserId(long userId, long from, long size) {
        return null;
    }

    @Override
    public Event getEventByIdAndByUserId(long userId, long eventId) {
        return null;
    }

    @Override
    public List<Event> getAllEventsAdmin(List<Long> usersIds, List<String> states, List<Long> categories,
                                         String rangeStart, String rangeEnd, long from, long size) {
        return null;
    }

    @Override
    public Event saveEvent(long userId, EventDto eventDto) {
        Event event = EventMapper.toEvent(eventDto);
        event.setCategory(categoryRepository.getById(eventDto.getCategoryId()));
        return repository.save(event);
    }

    @Override
    public Event updateEvent(long userId, long eventId, EventDto eventDto) {
        return null;
    }

    @Override
    public Event updateEventAdmin(long eventId, EventDto eventDto) {
        return null;
    }
}