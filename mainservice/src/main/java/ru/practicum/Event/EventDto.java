package ru.practicum.Event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.annotation.After;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDto {
    String annotation;
    Long categoryId;
    String description;
    LocalDateTime eventDate;
    Boolean paid;
    Long participantLimit;
    Boolean requestModeration;
    String title;
}
