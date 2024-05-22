package com.pedidos.controller;

import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;
import com.pedidos.service.PedidoProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Pedidos", description = "API para consumir informações sobre pedidos.")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    private final PedidoProducer pedidoProducer;

    public PedidoController(PedidoRepository pedidoRepository, PedidoProducer pedidoProducer) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProducer = pedidoProducer;
    }

    @Operation(summary = "O valor total de um pedido.", description = "Retorna o valor total de um pedido.")
    @GetMapping("/pedidos/{codigoPedido}/total/")
    public double getValorTotal(@PathVariable long codigoPedido) {
        Pedido pedido = pedidoRepository.findFirstByCodigoPedido(codigoPedido).orElse(null);
        return pedido != null ? pedido.getItens().stream().mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum() : 0.0;
    }

    @Operation(summary = "Quantidade de pedidos de um determinado cliente.", description = "Retorna a quantidade de pedidos de um cliente.")
    @GetMapping("/clientes/{codigoCliente}/pedidos/quantidade")
    public long getQuantidadePedidosPorCliente(@PathVariable long codigoCliente) {
        return pedidoRepository.findByCodigoCliente(codigoCliente).size();
    }

    @Operation(summary = "Lista de pedidos de um determinado cliente.", description = "Retorna a lista de pedidos de um cliente.")
    @GetMapping("/clientes/{codigoCliente}/pedidos/")
    public List<Pedido> getPedidosPorCliente(@PathVariable long codigoCliente) {
        return pedidoRepository.findByCodigoCliente(codigoCliente);
    }

    @PostMapping("/send")
    public String sendPedido(@RequestBody Pedido pedido) {
        pedidoProducer.send(pedido);
        return "Pedido enviado para a fila";
    }
}
