package br.com.nlw.events.controllers;

import br.com.nlw.events.dto.EventDto;
import br.com.nlw.events.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventControllers {
    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<EventDto> saveEvent(@RequestBody @Valid EventDto eventDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.addNewEvent(eventDto));
    }
}
