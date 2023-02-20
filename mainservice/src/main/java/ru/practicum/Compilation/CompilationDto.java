package ru.practicum.Compilation;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.Event.EventFullDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationDto {
    Long id;

    List<EventFullDto> events;

    Boolean pinned;

    String title;
}