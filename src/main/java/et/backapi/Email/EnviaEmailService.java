package et.backapi.Email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EnviaEmailService {

    @Autowired
    private final JavaMailSender envioEmaildoJava;

    public EnviaEmailService(final JavaMailSender javaMailSender) {
        this.envioEmaildoJava = javaMailSender;
    }

    public void enviar(String para, String titulo,String conteudo){
        log.info("Enviando email pra confirmação de cadastro..");

        var mensagem = new SimpleMailMessage();

        mensagem.setTo(para);
        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        envioEmaildoJava.send(mensagem);
        log.info("E-mail enviado com sucesso!!");
    }
}
