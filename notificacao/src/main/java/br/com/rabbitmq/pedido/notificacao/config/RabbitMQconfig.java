package br.com.rabbitmq.pedido.notificacao.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQconfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeDLXName;

    @Value("${rabbitmq.queue.dlq.name}")
    private String queueDLQName;

    @Bean
    public FanoutExchange pedidoExchange(){
        return  new FanoutExchange(exchangeName);
    }
    @Bean
    public Queue notificacaoQueue(){
        Map<String,Object> filaerro = new HashMap<>();
        filaerro.put("x-dead-letter-exchange",exchangeDLXName);
        return  new Queue(queueName,true,false,false,filaerro);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(notificacaoQueue()).to(pedidoExchange());
    }
    @Bean
    public FanoutExchange pedidoDLXExchange(){
        return  new FanoutExchange(exchangeDLXName);
    }
    @Bean
    public Queue notificacaoDLQQueue(){
        return  new Queue(queueDLQName);
    }
    @Bean
    public Binding bindingDLX(){
        return BindingBuilder.bind(notificacaoDLQQueue()).to(pedidoDLXExchange());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory){
        return  new RabbitAdmin(connectionFactory);
    }
    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory, MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener  (RabbitAdmin rabbitAdmin){
        return  event -> rabbitAdmin.initialize();
    }
}
