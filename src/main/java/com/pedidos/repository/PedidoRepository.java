package com.pedidos.repository;


import com.pedidos.model.Pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    Optional<Pedido> findFirstByCodigoPedido (int codigoPedido);
    List<Pedido> findByCodigoCliente(int codigoCliente);
}
