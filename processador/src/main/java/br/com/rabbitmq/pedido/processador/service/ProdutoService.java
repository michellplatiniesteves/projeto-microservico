package br.com.rabbitmq.pedido.processador.service;

import br.com.rabbitmq.pedido.processador.entity.ItemPedido;
import br.com.rabbitmq.pedido.processador.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void salvar(List<ItemPedido> itemPedido) {
        itemPedido.forEach(item ->{
                produtoRepository.save(item.getProdutos());
        });
    }
}
