package org.id.bankspringbatch;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {
    @Value(("${udemy.rabbitmq.queue}"))
    String queueName;
    @Value(("${udemy.rabbitmq.exchange}"))
    String exchange;
    @Value(("${udemy.rabbitmq.routingkey}"))
    String routingkey;

    @Bean
    Queue queue(){
        return new Queue(queueName,true);

    }
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchange);
    }
   @Bean
    Binding binding(Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(routingkey);
    }
   @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
