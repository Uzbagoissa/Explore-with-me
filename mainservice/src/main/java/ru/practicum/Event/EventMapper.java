package ru.practicum.Event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.Category.Category;
import ru.practicum.Category.CategoryDto;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventMapper {

    public static EventDto toEventDto(Event event) {
        return new EventDto(
                event.getAnnotation(),
                event.getCategory().getId()
        );
    }

    public static Event toEvent(EventDto eventDto) {
        Event event = new Event();
        event.setAnnotation(eventDto.getAnnotation());
        return event;
    }

    /*public static List<CategoryDto> toListCategoryDto(Iterable<Category> categories) {
        List<CategoryDto> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(toCategoryDto(category));
        }
        return result;
    }*/

}