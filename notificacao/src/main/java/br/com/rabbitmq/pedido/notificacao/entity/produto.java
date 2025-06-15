package br.com.rabbitmq.pedido.notificacao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class produto {
    private UUID id = UUID.randomUUID();
    private Double valor;
    private String nome;
}
