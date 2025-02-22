package br.com.nlw.events.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventDto(
        @NotBlank @NotNull String title,
        @NotBlank @NotNull String prettyName,
        @NotBlank @NotNull String location,
        @NotBlank @NotNull Double price,
        @NotBlank @NotNull LocalDate startDate,
        @NotBlank @NotNull LocalDate endDate,
        @NotBlank @NotNull LocalTime startTime,
        @NotBlank @NotNull LocalTime endTime
        ) {
}
