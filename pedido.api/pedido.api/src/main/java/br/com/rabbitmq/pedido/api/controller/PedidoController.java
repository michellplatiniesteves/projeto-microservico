package br.com.rabbitmq.pedido.api.controller;

import br.com.rabbitmq.pedido.api.entity.Pedido;

import br.com.rabbitmq.pedido.api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController
@Tag(name = "Pedido",description = "Controller de pedidos")
@RequestMapping(value = "/api/v1/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Criar o pedido",description = "Cadastrar o pedido",
              responses = @ApiResponse(responseCode = "201",description = "Pedido cadastrado com sucesso",
                      content = @Content(mediaType = "application/json",schema = @Schema(implementation = Pedido.class))
              )
    )
    @PostMapping
    public ResponseEntity<Pedido>criarPedido(@RequestBody Pedido pedido){
        logger.info("Pedido Criado"+pedido.toString());
        pedido = pedidoService.enfileirarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
