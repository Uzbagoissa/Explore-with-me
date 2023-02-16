package ru.practicum.Event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.Category.Category;
import ru.practicum.Category.CategoryDto;
import ru.practicum.User.UserDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventMapper {

    public static FullEventDto toFullEventDto(Event event, CategoryDto categoryDto, UserDto userDto) {
        FullEventDto fullEventDto = new FullEventDto();
        fullEventDto.setId(event.getId());
        fullEventDto.setAnnotation(event.getAnnotation());
        fullEventDto.setCategory(categoryDto);
        fullEventDto.setConfirmedRequests(event.getConfirmedRequests());
        fullEventDto.setCreatedOn(localDateTimeToString(event.getCreatedOn()));
        fullEventDto.setDescription(event.getDescription());
        fullEventDto.setEventDate(localDateTimeToString(event.getEventDate()));
        fullEventDto.setInitiator(userDto);
        fullEventDto.setLocation(new Location(event.getLat(), event.getLon()));
        fullEventDto.setPaid(event.getPaid());
        fullEventDto.setParticipantLimit(event.getParticipantLimit());
        fullEventDto.setPublishedOn(localDateTimeToString(event.getPublishedOn()));
        fullEventDto.setRequestModeration(event.getRequestModeration());
        fullEventDto.setState(event.getState());
        fullEventDto.setTitle(event.getTitle());
        fullEventDto.setViews(event.getViews());
        return fullEventDto;
    }

    public static Event toEvent(NewEventDto newEventDto, Long confirmedRequests, LocalDateTime createdOn, long userId,
                                LocalDateTime publishedOn, String state, Long views) {
        Event event = new Event();
        event.setAnnotation(newEventDto.getAnnotation());
        event.setCategory(newEventDto.getCategory());
        event.setConfirmedRequests(confirmedRequests);
        event.setCreatedOn(createdOn);
        event.setDescription(newEventDto.getDescription());
        event.setEventDate(stringToLocalDateTime(newEventDto.getEventDate()));
        event.setInitiatorId(userId);
        event.setLat(newEventDto.getLocation().getLat());
        event.setLon(newEventDto.getLocation().getLon());
        event.setPaid(newEventDto.getPaid());
        event.setParticipantLimit(newEventDto.getParticipantLimit());
        event.setPublishedOn(publishedOn);
        event.setRequestModeration(newEventDto.getRequestModeration());
        event.setState(state);
        event.setTitle(newEventDto.getTitle());
        event.setViews(views);
        return event;
    }

    public static Event toEvent(NewEventDtoWithState newEventDtoWithState, Long confirmedRequests, LocalDateTime createdOn,
                                long userId, LocalDateTime publishedOn, String state, Long views) {
        Event event = new Event();
        event.setAnnotation(newEventDtoWithState.getAnnotation());
        event.setCategory(newEventDtoWithState.getCategory());
        event.setConfirmedRequests(confirmedRequests);
        event.setCreatedOn(createdOn);
        event.setDescription(newEventDtoWithState.getDescription());
        event.setEventDate(stringToLocalDateTime(newEventDtoWithState.getEventDate()));
        event.setInitiatorId(userId);
        event.setLat(newEventDtoWithState.getLocation().getLat());
        event.setLon(newEventDtoWithState.getLocation().getLon());
        event.setPaid(newEventDtoWithState.getPaid());
        event.setParticipantLimit(newEventDtoWithState.getParticipantLimit());
        event.setPublishedOn(publishedOn);
        event.setRequestModeration(newEventDtoWithState.getRequestModeration());
        event.setState(state);
        event.setTitle(newEventDtoWithState.getTitle());
        event.setViews(views);
        event.setStateAction(newEventDtoWithState.getStateAction());
        return event;
    }

    public static LocalDateTime stringToLocalDateTime(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(timestamp, formatter);
        return localDateTime;
    }

    public static String localDateTimeToString(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localDateTime = timestamp.format(formatter);
        return localDateTime;
    }

}