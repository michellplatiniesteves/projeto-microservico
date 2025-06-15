package br.com.rabbitmq.pedido.notificacao.listener;

import br.com.rabbitmq.pedido.notificacao.entity.Pedido;
import br.com.rabbitmq.pedido.notificacao.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {
    private final Logger logger = LoggerFactory.getLogger(PedidoListener.class);
    private final EmailService emailService;

    public PedidoListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado-gerar-notificacao")
    public void enviarNotificacao(Pedido pedido){
        if(pedido.getTotal()<=24){
            throw  new RuntimeException("Erro ao enviar email");
        }
        emailService.enviarEmail(pedido);
        logger.info("Notificação gerada "+ pedido.toString());

    }
}
