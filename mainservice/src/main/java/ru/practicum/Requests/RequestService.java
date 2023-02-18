package ru.practicum.Requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.User.UserDto;

import java.util.List;

public interface RequestService {
    List<RequestDto> getAllRequestsByUserId(long userId);

    RequestDto saveRequest(long userId, long eventId);

    RequestDto cancelRequest(long userId, long requestId);

    void updateRequestsStatus(long userId, long eventId);
}