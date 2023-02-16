package ru.practicum.Event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.Category.*;
import ru.practicum.User.UserDto;
import ru.practicum.User.UserMapper;
import ru.practicum.User.UserRepository;
import ru.practicum.exceptions.ConflictException;
import ru.practicum.exceptions.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<FullEventDto> getAllEvents(String text, List<Long> categories, boolean paid, String rangeStart, String rangeEnd, boolean onlyAvailable, String sort, long from, long size) {
        return null;
    }

    @Override
    public FullEventDto getEventById(long id) {
        eventValid(id);
        if (repository.getById(id).getState().equals(StateEnum.PUBLISHED.toString())){
            return toFullEventDtoAbsolutely(repository.findEventById(id));
        } else {
            log.error("Можно найти только опубликованное событие");
            throw new NotFoundException("Можно найти только опубликованное событие");
        }
    }

    @Override
    public List<FullEventDto> getAllEventsByUserId(long userId, long from, long size) {
        userValid(userId);
        return repository.findEventsByUserId(userId).stream()
                .skip(from)
                .limit(size)
                .map(this::toFullEventDtoAbsolutely)
                .collect(Collectors.toList());
    }

    @Override
    public FullEventDto getEventByIdAndByUserId(long userId, long eventId) {
        userValid(userId);
        eventValid(eventId);
        return toFullEventDtoAbsolutely(repository.findEventByIdAndByUserId(eventId, userId));
    }

    @Override
    public List<FullEventDto> getAllEventsAdmin(List<Long> userIds, List<String> states, List<Long> categories,
                                                String rangeStart, String rangeEnd, long from, long size) {
        LocalDateTime start = EventMapper.stringToLocalDateTime(rangeStart);
        LocalDateTime end = EventMapper.stringToLocalDateTime(rangeEnd);
        List<Event> events = new ArrayList<>();
        if (states.size() == 0) {
            for (Long category : categories) {
                for (Long userId : userIds) {
                    events.addAll(repository.findEventsInRangeByCategoryIdByUserId(start, end, category, userId));
                }
            }
        } else if (userIds.size() == 0) {
            for (String state : states) {
                for (Long category : categories) {
                    events.addAll(repository.findEventsInRangeByStateByCategoryId(start, end, state, category));
                }
            }
        } else if (categories.size() == 0) {
            for (String state : states) {
                for (Long userId : userIds) {
                    events.addAll(repository.findEventsInRangeByStateIdByUserId(start, end, state, userId));
                }
            }
        }
        if (categories.size() == 0 && states.size() == 0) {
            for (Long userId : userIds) {
                events.addAll(repository.findEventsInRangeByUserId(start, end, userId));
            }
        } else if (categories.size() == 0 && userIds.size() == 0) {
            for (String state : states) {
                events.addAll(repository.findEventsInRangeByState(start, end, state));
            }
        } else if (states.size() == 0 && userIds.size() == 0) {
            for (Long category : categories) {
                events.addAll(repository.findEventsInRangeByCategoryId(start, end, category));
            }
        }
        for (String state : states) {
            for (Long category : categories) {
                for (Long userId : userIds) {
                    events.addAll(repository.findEventsInRangeByStateByCategoryIdByUserId(start, end, state, category, userId));
                }
            }
        }
        return events.stream()
                .skip(from)
                .limit(size)
                .map(this::toFullEventDtoAbsolutely)
                .collect(Collectors.toList());
    }

    @Override
    public FullEventDto saveEvent(long userId, NewEventDto newEventDto) {
        userValid(userId);
        eventDateValid(EventMapper.stringToLocalDateTime(newEventDto.getEventDate()));
        Event event = EventMapper.toEvent(newEventDto, 0L, LocalDateTime.now(), userId, LocalDateTime.now(),
                StateEnum.PENDING.toString(), 0L);
        repository.save(event);
        FullEventDto fullEventDto = EventMapper.toFullEventDto(event,
                CategoryMapper.toCategoryDto(categoryRepository.getById(newEventDto.getCategory())),
                UserMapper.toUserDto(userRepository.getById(userId)));
        return fullEventDto;
    }

    @Override
    public FullEventDto updateEvent(long userId, long eventId, NewEventDtoWithState newEventDtoWithState) {
        userValid(userId);
        eventValid(eventId);
        eventPublishValid(eventId);
        eventDateValid(EventMapper.stringToLocalDateTime(newEventDtoWithState.getEventDate()));
        Event event = repository.getById(eventId);
        if (event.getInitiatorId() != userId) {
            log.error("Пользователь с id {} не может изменять это событие с id {}!", userId, eventId);
            throw new ConflictException("Пользователю запрещено изменять чужое событие!");
        }
        Event updatedEvent = EventMapper.toEvent(newEventDtoWithState, event.getConfirmedRequests(), event.getCreatedOn(),
                userId, event.getPublishedOn(), StateEnum.PENDING.toString(), event.getViews());
        updatedEvent.setId(eventId);
        repository.save(updatedEvent);
        return toFullEventDtoAbsolutely(updatedEvent);
    }

    @Override
    public FullEventDto updateEventAdmin(long eventId, NewEventDtoWithState newEventDtoWithState) {
        eventPublishValid(eventId);
        eventDateValidAdmin(EventMapper.stringToLocalDateTime(newEventDtoWithState.getEventDate()));
        Event event = repository.getById(eventId);
        String state = "";
        if (newEventDtoWithState.getStateAction().equals(StateActionEnum.PUBLISH_EVENT.toString())) {
            state = StateEnum.PUBLISHED.toString();
        }
        if (newEventDtoWithState.getStateAction().equals(StateActionEnum.CANCEL_EVENT.toString())) {
            state = StateEnum.CANCELED.toString();
        }
        long userId = event.getInitiatorId();
        Event updatedEvent = EventMapper.toEvent(newEventDtoWithState, event.getConfirmedRequests(), event.getCreatedOn(), userId,
                LocalDateTime.now(), state, event.getViews());
        updatedEvent.setId(eventId);
        repository.save(updatedEvent);
        return toFullEventDtoAbsolutely(updatedEvent);
    }

    private FullEventDto toFullEventDtoAbsolutely(Event event) {
        return EventMapper.toFullEventDto(event,
                CategoryMapper.toCategoryDto(categoryRepository.getById(event.getCategory())),
                UserMapper.toUserDto(userRepository.getById(event.getInitiatorId())));
    }

    private void eventPublishValid(long eventId) {
        if (repository.getById(eventId).getState().equals(StateEnum.PUBLISHED.toString())) {
            log.error("Опубликованное событие c id {} изменить нельзя", eventId);
            throw new ConflictException("Опубликованное событие изменить нельзя");
        }
    }

    private void userValid(long userId) {
        if (userRepository.findUserById(userId) == null) {
            log.error("Пользователь c {} не найден или недоступен", userId);
            throw new NotFoundException("Пользователь не найден или недоступен");
        }
    }

    private void eventValid(long eventId) {
        if (repository.findEventById(eventId) == null) {
            log.error("Событие c {} не найдено или недоступно", eventId);
            throw new NotFoundException("Событие не найдено или недоступно");
        }
    }

    private void eventDateValid(LocalDateTime eventDate) {
        if (eventDate.isBefore(LocalDateTime.now().plusHours(2))) {
            log.error("Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента");
            throw new ConflictException("Дата и время на которые намечено событие не может быть раньше, чем через два часа от текущего момента");
        }
    }

    private void eventDateValidAdmin(LocalDateTime eventDate) {
        if (eventDate.isBefore(LocalDateTime.now().minusHours(1))) {
            log.error("дата начала изменяемого события должна быть не ранее чем за час от даты публикации");
            throw new ConflictException("дата начала изменяемого события должна быть не ранее чем за час от даты публикации");
        }
    }
}