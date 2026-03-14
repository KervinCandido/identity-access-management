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

public class UpdateUserEventPublisher implements EventPublisher<User> {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserEventPublisher.class);

    public static final String USER_UPDATE_EVENT_TYPE = "user.update";
    private final RabbitTemplate rabbitTemplate;

    public UpdateUserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public CompletableFuture<Void> publish(User event) {
        var updateUserEvent = new EventDTO<>(USER_UPDATE_EVENT_TYPE, new UserDTO(event));
        log.info("Publicando UpdateUserEvent: {}", updateUserEvent);
        return CompletableFuture.runAsync(() -> rabbitTemplate
                .convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_UPDATE_ROUTING_KEY, updateUserEvent));
    }
}
