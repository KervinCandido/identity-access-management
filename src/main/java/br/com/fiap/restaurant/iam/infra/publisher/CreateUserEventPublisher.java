package br.com.fiap.restaurant.iam.infra.publisher;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.gateway.EventPublisher;
import br.com.fiap.restaurant.iam.infra.config.RabbitMQConfig;
import br.com.fiap.restaurant.iam.infra.publisher.dto.EventDTO;
import br.com.fiap.restaurant.iam.infra.publisher.dto.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.concurrent.CompletableFuture;

public class CreateUserEventPublisher implements EventPublisher<User> {

    public static final String USER_CREATE_EVENT_TYPE = "user.create";
    private final RabbitTemplate rabbitTemplate;

    public CreateUserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public CompletableFuture<Void> publish(User event) {
        var createUserEvent = new EventDTO<>(USER_CREATE_EVENT_TYPE, new UserDTO(event));

        return CompletableFuture.runAsync(() -> rabbitTemplate
                .convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_CREATE_ROUTING_KEY, createUserEvent));
    }
}
