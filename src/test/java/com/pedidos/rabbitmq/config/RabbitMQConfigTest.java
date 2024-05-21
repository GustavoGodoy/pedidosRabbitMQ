package com.pedidos.rabbitmq.config;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RabbitMQConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testExchange() {
        TopicExchange exchange = (TopicExchange) context.getBean("exchange");
        assertNotNull(exchange);
        assertEquals("pedidoExchange", exchange.getName());
    }

    @Test
    void testBinding() {
        Binding binding = (Binding) context.getBean("binding");
        assertNotNull(binding);
        assertEquals("pedidoQueue", binding.getDestination());
        assertEquals("pedidoExchange", binding.getExchange());
        assertEquals("pedidoRoutingKey", binding.getRoutingKey());
    }

    @Test
    void testFila() {
        Queue queue = context.getBean(Queue.class);
        assertNotNull(queue, "O Bean da fila não pode ser nulo");
        assertEquals("pedidoQueue", queue.getName(), "O nome da fila deve ser 'pedidoQueue'");
        assertEquals(false, queue.isDurable(), "A fila não pode ser duravel");
    }
}