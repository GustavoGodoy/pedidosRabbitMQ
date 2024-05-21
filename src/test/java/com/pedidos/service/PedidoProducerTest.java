package com.pedidos.service;

import com.pedidos.model.Item;
import com.pedidos.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class PedidoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PedidoProducer pedidoProducer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testSend() {
        Item item1 = new Item("lápis", 100, 1.10);
        Item item2 = new Item("caderno", 10, 2.50);
        Pedido pedido = new Pedido(null, 1001, 1, Arrays.asList(item1, item2));

        pedidoProducer.send(pedido);

        ArgumentCaptor<String> exchangeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> routingKeyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> messageCaptor = ArgumentCaptor.forClass(Object.class);

        verify(rabbitTemplate).convertAndSend(
                exchangeCaptor.capture(),
                routingKeyCaptor.capture(),
                messageCaptor.capture()
        );

        assertEquals("pedidoExchange", exchangeCaptor.getValue());
        assertEquals("pedidoRoutingKey", routingKeyCaptor.getValue());
        assertEquals(pedido, messageCaptor.getValue());
    }
}