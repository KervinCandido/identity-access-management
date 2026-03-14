package br.com.fiap.restaurant.iam.infra.publisher.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDTO<T>(UUID uuid, String type, LocalDateTime createTimeStamp, T body) {
    public EventDTO(String type, T body) {
        this(UUID.randomUUID(), type, LocalDateTime.now(), body);
    }
}
