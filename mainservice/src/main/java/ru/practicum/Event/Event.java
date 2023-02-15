package ru.practicum.Event;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.Category.CategoryDto;
import ru.practicum.User.UserDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "events", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "annotation", nullable = false)
    String annotation;

    @Column(name = "category", nullable = false)
    Long categoryId;

    @Column(name = "confirmedRequests", nullable = false)
    Long confirmedRequests;

    @Column(name = "createdOn", nullable = false)
    LocalDateTime createdOn;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "eventDate", nullable = false)
    LocalDateTime eventDate;

    @Column(name = "initiator", nullable = false)
    Long initiatorId;

    @Column(name = "paid", nullable = false)
    Boolean paid;

    @Column(name = "participantLimit", nullable = false)
    Long participantLimit;

    @Column(name = "publishedOn", nullable = false)
    LocalDateTime publishedOn;

    @Column(name = "requestModeration", nullable = false)
    Boolean requestModeration;

    @Column(name = "state", nullable = false)
    String state;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "views", nullable = false)
    Long views;

    @Transient
    CategoryDto categoryDto;

    @Transient
    UserDto userDto;

}
