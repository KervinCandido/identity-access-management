package br.com.fiap.restaurant.iam.infra.controller.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record OpeningHoursResponse (
    @DateTimeFormat(pattern = "EEEE") DayOfWeek dayOfWeek,
    @DateTimeFormat(pattern = "HH:mm") LocalTime openHour,
    @DateTimeFormat(pattern = "HH:mm")LocalTime closeHour
){}
