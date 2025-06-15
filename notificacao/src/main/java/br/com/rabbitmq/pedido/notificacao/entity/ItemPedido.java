package br.com.rabbitmq.pedido.notificacao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    private UUID id = UUID.randomUUID();
    private produto produtos;
    private Integer quantidade;
}
