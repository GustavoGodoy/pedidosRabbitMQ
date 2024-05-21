package com.pedidos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void testGettersESetters() {
        Item item = new Item();

        item.setProduto("lápis");
        item.setQuantidade(100);
        item.setPreco(1.10);

        assertEquals("lápis", item.getProduto());
        assertEquals(100, item.getQuantidade());
        assertEquals(1.10, item.getPreco());
    }

    @Test
    void testItemConstrutor() {
        Item item = new Item("caderno", 10, 2.50);

        assertEquals("caderno", item.getProduto());
        assertEquals(10, item.getQuantidade());
        assertEquals(2.50, item.getPreco());
    }
}
