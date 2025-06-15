package br.com.rabbitmq.pedido.notificacao.entity;

import br.com.rabbitmq.pedido.notificacao.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    private UUID id = UUID.randomUUID();
    private List<ItemPedido> itemPedido = new ArrayList<>();
    private String cliente;
    private Double total;
    private String email;
    private Status status = Status.EM_PROCESSAMENTO;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

}
