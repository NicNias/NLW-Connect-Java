package br.com.nlw.events.controllers;

import br.com.nlw.events.dto.SubscriptionResponseDto;
import br.com.nlw.events.dto.UserDto;
import br.com.nlw.events.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionControllers {
    private final SubscriptionService subscriptionService;

    @PostMapping({"/{prettyName}", "/{prettyName}/{userId}"})
    public ResponseEntity<SubscriptionResponseDto> saveSub(@PathVariable String prettyName, @RequestBody @Valid UserDto user, @PathVariable(required = false) Integer userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createNewSubscription(prettyName, user, userId));
    }
}
