package br.com.fiap.restaurant.iam.infra.publisher;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.gateway.EventPublisher;
import br.com.fiap.restaurant.iam.infra.config.RabbitMQConfig;
import br.com.fiap.restaurant.iam.infra.publisher.dto.EventDTO;
import br.com.fiap.restaurant.iam.infra.publisher.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class DeleteUserEventPublisher implements EventPublisher<User> {

    private static final Logger log = LoggerFactory.getLogger(DeleteUserEventPublisher.class);

    public static final String USER_DELETE_EVENT_TYPE = "user.delete";
    private final RabbitTemplate rabbitTemplate;

    public DeleteUserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public CompletableFuture<Void> publish(User event) {
        var deleteUserEvent = new EventDTO<>(USER_DELETE_EVENT_TYPE, new UserDTO(event.getId(), Set.of()));
        log.info("Publicando DeleteUserEvent: {}", deleteUserEvent);
        return CompletableFuture.runAsync(() -> rabbitTemplate
                .convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_DELETE_ROUTING_KEY, deleteUserEvent));
    }
}
