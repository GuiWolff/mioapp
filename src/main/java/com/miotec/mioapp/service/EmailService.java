package com.miotec.mioapp.service;

import com.miotec.mioapp.domain.Usuario;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmailDeResetarSenha(Usuario usuario, String senha) throws MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Recuperação de senha App Pelvifit Trainer ");

        String html =
        "<meta id = \"metaname\" name=\"IoB \" content=\"width=device-width,initial-scale=2.0\" charset=\"UTF-8\">"+
        "<table width=\"692\" width=\"100%\"cellspacing=\"0\" cellpadding=\"0\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"background-color: #838383;\" align=\"center\" valign=\"top\" bgcolor=\"#838383\"><br /><br />\n" +
                "<table style=\"width: 598px; height: 403px;\" border=\"0\" width=\"600\" cellspacing=\"20\" cellpadding=\"0\">\n" +
                "<tbody>\n" +
                "<tr style=\"height: 393px;\">\n" +
                "<td style=\"background-color: #dcffff; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px; width: 524px; height: 393px;\" align=\"center\" valign=\"top\" bgcolor=\"#d3be6c\">\n" +
                "<div style=\"font-size: 36px; color: blue;\">&nbsp;</div>\n" +
                "<div style=\"font-size: 36px; color: blue;\"><span style=\"color: #555100;\"><strong>Ol&aacute "+usuario.getNome()+"</strong></span></div>\n" +
                "<div style=\"font-size: 24px; color: #555100;\"><br />Este &eacute; um e-mail autom&aacute;tico para recupera&ccedil;&atilde;o da sua senha! Sua senha atual &eacute; <strong><span style=\"background-color: #fff59d; color: #663300;\">"+senha+"</span> !!! <br /></strong></div>\n" +
                "<div><br />Para acessar o aplicativo <strong>Pelvifit Trainer</strong> use a senha tempor&aacute;ria, que est&aacute; com fundo amarelo!</div>\n" +
                "<div>Ap&oacute;s se logar no aplicativo, voc&ecirc; pode alterar sua senha no campo <strong>Configura&ccedil;&otilde;es &gt; Cadastro</strong>.<br /><br />Tenha &oacute;timos treinos!&nbsp;<br /><br /><br /><br /><strong>Att,</strong><br />Equipe Miotec.</div>\n" +
                "<div>"+ LocalDateTime.now().getDayOfMonth()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear()+"</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr style=\"height: 0px;\">\n" +
                "<td style=\"background-color: #838383; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 0px 0px; width: 524px; height: 0 px;\">&nbsp;</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<br /><br /></td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";


        helper.setText(html, true);
        helper.setTo(usuario.getEmail());
        javaMailSender.send(mimeMessage);
    }


    public void enviarEmailDeCriarConta(Usuario usuario, String senha) throws MessagingException {
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Criação de conta App Pelvifit Trainer ");

        String html =
                "<meta id = \"metaname\" name=\"IoB \" content=\"width=device-width,initial-scale=2.0\" charset=\"UTF-8\">"+
                        "<table width=\"692\" width=\"100%\"cellspacing=\"0\" cellpadding=\"0\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"background-color: #838383;\" align=\"center\" valign=\"top\" bgcolor=\"#838383\"><br /><br />\n" +
                        "<table style=\"width: 598px; height: 403px;\" border=\"0\" width=\"600\" cellspacing=\"20\" cellpadding=\"0\">\n" +
                        "<tbody>\n" +
                        "<tr style=\"height: 393px;\">\n" +
                        "<td style=\"background-color: #dcffff; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px; width: 524px; height: 393px;\" align=\"center\" valign=\"top\" bgcolor=\"#d3be6c\">\n" +
                        "<div style=\"font-size: 36px; color: blue;\">&nbsp;</div>\n" +
                        "<div style=\"font-size: 36px; color: blue;\"><span style=\"color: #555100;\"><strong>Ol&aacute "+usuario.getNome()+"</strong></span></div>\n" +
                        "<div style=\"font-size: 24px; color: #555100;\"><br />Este &eacute; um e-mail autom&aacute;tico para recupera&ccedil;&atilde;o da sua senha! Sua senha atual &eacute; <strong><span style=\"background-color: #fff59d; color: #663300;\">"+senha+"</span> !!! <br /></strong></div>\n" +
                        "<div><br />Para acessar o aplicativo <strong>Pelvifit Trainer</strong> use a senha tempor&aacute;ria, que est&aacute; com fundo amarelo!</div>\n" +
                        "<div>Ap&oacute;s se logar no aplicativo, voc&ecirc; pode alterar sua senha no campo <strong>Configura&ccedil;&otilde;es &gt; Cadastro</strong>.<br /><br />Tenha &oacute;timos treinos!&nbsp;<br /><br /><br /><br /><strong>Att,</strong><br />Equipe Miotec.</div>\n" +
                        "<div>"+ LocalDateTime.now().getDayOfMonth()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear()+"</div>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "<tr style=\"height: 0px;\">\n" +
                        "<td style=\"background-color: #838383; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 0px 0px; width: 524px; height: 0 px;\">&nbsp;</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "<br /><br /></td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>";


        helper.setText(html, true);
        helper.setTo(usuario.getEmail());
        javaMailSender.send(mimeMessage);
    }

}