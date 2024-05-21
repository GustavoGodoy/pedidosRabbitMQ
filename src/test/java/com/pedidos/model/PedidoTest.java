package com.pedidos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PedidoTest {
    @Test
     void testGettersESetters() {
        Pedido pedido = new Pedido();
        Item item1 = new Item("lápis", 100, 1.10);
        Item item2 = new Item("caderno", 10, 2.50);
        List<Item> itens = Arrays.asList(item1, item2);

        pedido.setId("1");
        pedido.setCodigoPedido(1001);
        pedido.setCodigoCliente(1);
        pedido.setItens(itens);

        assertEquals("1", pedido.getId());
        assertEquals(1001, pedido.getCodigoPedido());
        assertEquals(1, pedido.getCodigoCliente());
        assertEquals(itens, pedido.getItens());
    }

    @Test
     void testPedidoConstrutor() {
        Item item1 = new Item("lápis", 100, 1.10);
        Item item2 = new Item("caderno", 10, 2.50);
        List<Item> itens = Arrays.asList(item1, item2);

        Pedido pedido = new Pedido("1", 1001, 1, itens);

        assertEquals("1", pedido.getId());
        assertEquals(1001, pedido.getCodigoPedido());
        assertEquals(1, pedido.getCodigoCliente());
        assertEquals(itens, pedido.getItens());
    }
}
