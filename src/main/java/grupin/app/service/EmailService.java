package grupin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import grupin.app.model.EmailModel;

/**
 * @author Lucas-dev-back
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * 
     * @param emailModel objeto de email
     * @throws MailException Exceção para caso o email enviado ocorra algum erro
     */
    public void sendEmail(EmailModel emailModel) {
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setReplyTo(emailModel.getDestinario());
            mensagem.setSubject(emailModel.getAssunto());
            mensagem.setText(emailModel.getCorpo());
            emailSender.send(mensagem);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

}
