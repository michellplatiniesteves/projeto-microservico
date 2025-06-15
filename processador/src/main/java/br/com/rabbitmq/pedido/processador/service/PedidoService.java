package br.com.rabbitmq.pedido.processador.service;

import br.com.rabbitmq.pedido.processador.entity.ItemPedido;
import br.com.rabbitmq.pedido.processador.entity.Pedido;
import br.com.rabbitmq.pedido.processador.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private  final PedidoRepository pedidoRepository;
    private  final ItemPedidoService itemPedidoService;
    private  final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoService itemPedidoService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoService = itemPedidoService;
        this.produtoService = produtoService;
    }
    public void salvar(Pedido pedido){
        produtoService.salvar(pedido.getItemPedido());
        List<ItemPedido> itens = itemPedidoService.salvar(pedido.getItemPedido());
        pedidoRepository.save(pedido);
        itemPedidoService.updateItemPedido(itens,pedido);
        logger.info("Pedido Salvo com sucesso");
    }
}
