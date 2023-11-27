package et.backapi.Services;

import et.backapi.Entities.User;
import et.backapi.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean verificarTokenAutorizacao(String email, String token) {
        User user = userRepository.findByUserEmail(email);

        if (user != null) {
            LocalDateTime expiracao = convertToLocalDateTime(user.getUserConfirmEmailTokenExpiration());
            LocalDateTime agora = LocalDateTime.now();

            if (expiracao.isAfter(agora) && user.getUserConfirmEmailToken().equals(token)) {

                return true;
            }
        }

        return false;
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
