package br.com.fiap.restaurant.iam.infra.publisher;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.gateway.EventPublisher;
import br.com.fiap.restaurant.iam.infra.config.RabbitMQConfig;
import br.com.fiap.restaurant.iam.infra.publisher.dto.EventDTO;
import br.com.fiap.restaurant.iam.infra.publisher.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.concurrent.CompletableFuture;

public class CreateUserEventPublisher implements EventPublisher<User> {

    private static final Logger log = LoggerFactory.getLogger(CreateUserEventPublisher.class);

    public static final String USER_CREATE_EVENT_TYPE = "user.create";
    private final RabbitTemplate rabbitTemplate;

    public CreateUserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public CompletableFuture<Void> publish(User event) {
        var createUserEvent = new EventDTO<>(USER_CREATE_EVENT_TYPE, new UserDTO(event));
        log.info("Publicando CreateUserEvent: {}", createUserEvent);
        return CompletableFuture.runAsync(() -> rabbitTemplate
                .convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_CREATE_ROUTING_KEY, createUserEvent));
    }
}
