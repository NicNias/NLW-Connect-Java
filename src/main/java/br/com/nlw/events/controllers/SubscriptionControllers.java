package br.com.nlw.events.controllers;

import br.com.nlw.events.dto.SubscriptionDto;
import br.com.nlw.events.dto.UserDto;
import br.com.nlw.events.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subs")
public class SubscriptionControllers {
    private final SubscriptionService subscriptionService;

    @PostMapping("/create/{prettyName}")
    public ResponseEntity<SubscriptionDto> saveSub(@PathVariable String prettyName, @RequestBody @Valid UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createNewSubscription(prettyName, user));
    }
}
