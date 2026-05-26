package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarEmailCadastro(String destinatario, String nomeCliente, String textoMensagem) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Notificação ColdMart");
        message.setText("Olá, " + nomeCliente + "!\n\n" + textoMensagem);

        mailSender.send(message);
    }
}