package br.com.fiap.restaurant.iam.infra.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_EXCHANGE = "ex.user";
    public static final String RESTAURANT_USER_CREATE_QUEUE = "restaurant.user.create";
    public static final String RESTAURANT_USER_UPDATE_QUEUE = "restaurant.user.update";
    public static final String RESTAURANT_USER_DELETE_QUEUE = "restaurant.user.delete";

    public static final String USER_CREATE_ROUTING_KEY = "user.create";
    public static final String USER_UPDATE_ROUTING_KEY = "user.update";
    public static final String USER_DELETE_ROUTING_KEY = "user.delete";

    @Bean("userExchange")
    public DirectExchange userExchange() {
        return new DirectExchange(USER_EXCHANGE);
    }

    @Bean("restaurantUserCreateQueue")
    public Queue restaurantUserCreateQueue() {
        return QueueBuilder.durable(RESTAURANT_USER_CREATE_QUEUE).quorum().build();
    }

    @Bean("restaurantUserUpdateQueue")
    public Queue restaurantUserUpdateQueue() {
        return QueueBuilder.durable(RESTAURANT_USER_UPDATE_QUEUE).quorum().build();
    }

    @Bean("restaurantUserDeleteQueue")
    public Queue restaurantUserDeleteQueue() {
        return QueueBuilder.durable(RESTAURANT_USER_DELETE_QUEUE).quorum().build();
    }

    @Bean("bindingUserCreate")
    public Binding bindingUserCreate(@Qualifier("restaurantUserCreateQueue") Queue queue, @Qualifier("userExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(USER_CREATE_ROUTING_KEY);
    }

    @Bean("bindingUserUpdate")
    public Binding bindingUserUpdate(@Qualifier("restaurantUserUpdateQueue") Queue queue, @Qualifier("userExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(USER_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding bindingUserDelete(@Qualifier("restaurantUserDeleteQueue") Queue queue, @Qualifier("userExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(USER_DELETE_ROUTING_KEY);
    }

    @Bean
    public JacksonJsonMessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
