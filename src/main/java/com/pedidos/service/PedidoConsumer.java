package com.pedidos.service;

import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {

    private final PedidoRepository pedidoRepository;

    public PedidoConsumer(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @RabbitListener(queues = "pedidoQueue")
    public void receiveMessage(Pedido pedido) {
        Optional<Pedido> pedidoAntigo = pedidoRepository.findFirstByCodigoPedido(pedido.getCodigoPedido());
        pedidoAntigo.ifPresent(x -> pedidoRepository.deleteById(x.getId()));
        System.out.println("Pedido recebido via consumer: " + pedido);
        pedidoRepository.save(pedido);
    }
}
