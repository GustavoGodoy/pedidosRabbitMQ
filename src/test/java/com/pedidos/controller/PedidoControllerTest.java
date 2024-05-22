package com.pedidos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedidos.model.Item;
import com.pedidos.model.Pedido;
import com.pedidos.repository.PedidoRepository;
import com.pedidos.service.PedidoProducer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PedidoControllerTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoProducer pedidoProducer;

    @InjectMocks
    private PedidoController pedidoController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private Pedido pedido1;
    private Pedido pedido2;
    private List<Pedido> pedidos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
        objectMapper = new ObjectMapper();

        Item item1 = new Item("lápis", 100, 1.10);
        Item item2 = new Item("caderno", 10, 2.50);

        pedido1 = new Pedido("1", 1001, 1, Arrays.asList(item1));
        pedido2 = new Pedido("2", 1002, 1, Arrays.asList(item2));
        pedidos = Arrays.asList(pedido1, pedido2);
    }

    @Test
    void testGetValorTotal() {
        when(pedidoRepository.findFirstByCodigoPedido(1001)).thenReturn(Optional.of(pedido1));

        double valorTotal = pedidoController.getValorTotal(1001);
        assertEquals(110.0, valorTotal, 0.00001);
    }

    @Test
    public void testSendPedido() throws Exception {
        String pedidoJson = objectMapper.writeValueAsString(pedido1);

        mockMvc.perform(post("/api/v1/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido enviado para a fila"));

        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoProducer).send(pedidoCaptor.capture());

        Pedido capturedPedido = pedidoCaptor.getValue();
        assertEquals(pedido1.getCodigoPedido(), capturedPedido.getCodigoPedido());
    }

    @Test
    void testGetQuantidadePedidosPorCliente() {
        when(pedidoRepository.findByCodigoCliente(1)).thenReturn(pedidos);

        long quantidade = pedidoController.getQuantidadePedidosPorCliente(1);
        assertEquals(2, quantidade);
    }

    @Test
    void testGetPedidosPorCliente() {
        when(pedidoRepository.findByCodigoCliente(1)).thenReturn(pedidos);

        List<Pedido> result = pedidoController.getPedidosPorCliente(1);
        assertEquals(2, result.size());
        assertEquals(pedido1, result.get(0));
        assertEquals(pedido2, result.get(1));
    }
}