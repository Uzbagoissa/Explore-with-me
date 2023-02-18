package ru.practicum.Requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.User.UserDto;
import ru.practicum.exceptions.IncorrectParameterException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestDto> getAllRequestsByUserId(@PathVariable("userId") long userId) {
        log.info("Найдены запросы на участие");
        return requestService.getAllRequestsByUserId(userId);
    }

    @PostMapping("/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDto saveRequest(@PathVariable("userId") long userId,
                                  @RequestParam(value = "eventId") long eventId) {
        log.info("Заявка создана");
        return requestService.saveRequest(userId, eventId);
    }

    @PatchMapping("/requests/{requestId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public RequestDto cancelRequest(@PathVariable("userId") long userId,
                                    @PathVariable("requestId") long requestId) {
        log.info("Заявка отменена");
        return requestService.cancelRequest(userId, requestId);
    }

    /*@PatchMapping("/events/{eventId}/requests")
    @ResponseStatus(HttpStatus.OK)
    public RequestDto updateRequestsStatus(@PathVariable("userId") long userId,
                                    @PathVariable("eventId") long eventId,
                                    @Valid @RequestBody UserDto userDto) {
        log.info("Статус заявок изменён");
        return requestService.updateRequestsStatus(userId, eventId, userDto);
    }*/
}
