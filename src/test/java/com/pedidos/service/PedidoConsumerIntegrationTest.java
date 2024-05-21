package com.pedidos.service;

import com.pedidos.model.Item;
import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


//Essa classe deverá ser rodada apenas quando rabbitmq e mongodb estiverem no ar e devidamente conectados,
// cheque o application.properties para configurar a rota adequadamente.
//@SpringBootTest
//@ActiveProfiles("test")
//class PedidoConsumerIntegrationTest {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private Queue orderQueue;
//
//    @Autowired
//    private PedidoRepository pedidoRepository;
//
//    private Pedido pedido;
//
//    @BeforeEach
//    public void setUp() {
//        Random rd = new Random();
//        Item item1 = new Item("lápis", 100, 1.10);
//        Item item2 = new Item("caderno", 10, 2.50);
//        pedido = new Pedido(null, rd.nextInt(Integer.MAX_VALUE), 1, Arrays.asList(item1, item2));
//        rabbitTemplate.convertAndSend(orderQueue.getName(), pedido);
//    }
//
//    @Test
//    void testReceiveMessage() {
//        Optional<Pedido> savedPedido = pedidoRepository.findByCodigoPedido(pedido.getCodigoPedido());
//        assertTrue(savedPedido.isPresent());
//    }
//}
