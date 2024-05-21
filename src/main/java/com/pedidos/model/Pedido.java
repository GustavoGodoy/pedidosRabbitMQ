package com.pedidos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
@Schema(description = "Detalhes sobre o Pedido.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Schema(description = "ID gerado automaticamente pelo banco de dados")
    private String id;
    @Schema(description = "Codigo do pedido")
    private int codigoPedido;
    @Schema(description = "Codigo do cliente")
    private int codigoCliente;
    @Schema(description = "Lista de itens no pedido")
    private List<Item> itens;

}
