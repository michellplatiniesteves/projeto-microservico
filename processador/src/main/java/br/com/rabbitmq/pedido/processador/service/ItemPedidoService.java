package br.com.rabbitmq.pedido.processador.service;

import br.com.rabbitmq.pedido.processador.entity.ItemPedido;
import br.com.rabbitmq.pedido.processador.entity.Pedido;
import br.com.rabbitmq.pedido.processador.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private  final ItemPedidoRepository itemPedidoRepository;


    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;

    }
    public List<ItemPedido> salvar(List<ItemPedido> itens){
        List<ItemPedido> itemPedidos = itemPedidoRepository.saveAll(itens);
        return itemPedidos;

    }
public void salvar(ItemPedido pedido){
    itemPedidoRepository.save(pedido) ;
}
    public void updateItemPedido(List<ItemPedido> itens, Pedido pedido) {
        itens.forEach(item->{
            item.setPedido(pedido);
            this.salvar(item);
        });
    }
}
