package br.com.rabbitmq.pedido.notificacao.service;

import br.com.rabbitmq.pedido.notificacao.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender sender;


    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void enviarEmail(Pedido pedido){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("michellplatini@gmail.com");
        mailMessage.setTo(pedido.getEmail());
        mailMessage.setSubject("Pedido de compra");
        mailMessage.setText(this.getMensagem(pedido));
        sender.send(mailMessage);
    }

    private String getMensagem(Pedido pedido) {
        String id = pedido.getId().toString();
        String nome = pedido.getCliente().toString();
        String total = pedido.getTotal().toString();
        String status = pedido.getStatus().toString();
        return String.format("Ola %s seu pedido %s no valor de %s foi aprovado com sucesso. \n Status %s. ",nome,id,total,status);
    }
}
