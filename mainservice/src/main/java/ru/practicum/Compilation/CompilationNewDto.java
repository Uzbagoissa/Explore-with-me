package ru.practicum.Compilation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompilationNewDto {
    List<Long> events;

    Boolean pinned;

    String title;
}
