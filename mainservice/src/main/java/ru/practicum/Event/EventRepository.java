package ru.practicum.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.Category.Category;

public interface EventRepository extends JpaRepository<Event, Long> {
}