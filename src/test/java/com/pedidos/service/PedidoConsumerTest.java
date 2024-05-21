package com.pedidos.service;

import com.pedidos.model.Item;
import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import java.util.Arrays;

class PedidoConsumerTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoConsumer pedidoConsumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testReceiveMessage() {
        Item item1 = new Item("lápis", 100, 1.10);
        Item item2 = new Item("caderno", 10, 2.50);
        Pedido pedido = new Pedido(null, 1001, 1, Arrays.asList(item1, item2));

        pedidoConsumer.receiveMessage(pedido);

        verify(pedidoRepository).save(pedido);
    }
}
