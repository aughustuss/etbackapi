package et.backapi.adapter.email;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Service
public class EnviaEmailService {

    @Autowired
    private final JavaMailSender envioEmaildoJava;

    public EnviaEmailService(final JavaMailSender javaMailSender) {
        this.envioEmaildoJava = javaMailSender;
    }

    private static final Logger log = LoggerFactory.getLogger(EnviaEmailService.class);
    public void enviar(String para, String titulo,String conteudo){
        log.info("Enviando email pra confirmação de cadastro..");

        var mensagem = new SimpleMailMessage();

        mensagem.setTo(para);
        mensagem.setSubject(titulo);
        mensagem.setText(conteudo);
        envioEmaildoJava.send(mensagem);
        log.info("E-mail enviado com sucesso!!");
    }


    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}
