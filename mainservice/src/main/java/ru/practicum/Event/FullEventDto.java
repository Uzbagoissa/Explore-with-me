package ru.practicum.Event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.Category.CategoryDto;
import ru.practicum.User.UserDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullEventDto {
    Long id;
    String annotation;
    CategoryDto category;
    Long confirmedRequests;
    String createdOn;
    String description;
    String eventDate;
    UserDto initiator;
    Location location;
    Boolean paid;
    Long participantLimit;
    String publishedOn;
    Boolean requestModeration;
    String state;
    String title;
    Long views;
}