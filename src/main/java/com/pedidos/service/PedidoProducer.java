package com.pedidos.service;

import com.pedidos.model.Pedido;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Pedido pedido) {
        System.out.println("Pedido enviado com sucesso via producer.");
        rabbitTemplate.convertAndSend("pedidoExchange", "pedidoRoutingKey", pedido);
    }
}
