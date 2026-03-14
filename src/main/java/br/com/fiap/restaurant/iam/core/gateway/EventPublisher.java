package br.com.fiap.restaurant.iam.core.gateway;

import java.util.concurrent.CompletableFuture;

public interface EventPublisher <E> {
    CompletableFuture<Void> publish(E event);
}
