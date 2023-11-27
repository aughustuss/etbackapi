package et.backapi.Services;

import et.backapi.Entities.User;
import et.backapi.Repositories.UserRepository;
import et.backapi.Utils.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    HashPassword passwordHasher = new HashPassword();

    public boolean isOldPasswordCorrect(String userEmail, String oldPassword) {
        User user = userRepository.findByUserEmail(userEmail);

        return (user != null && passwordHasher.checkPassword(oldPassword, user.getUserPassword()));
    }

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

    public void resetPassword(String userEmail, String newPassword) {
        User user = userRepository.findByUserEmail(userEmail);

        if (user != null) {
            String hashedPassword = passwordHasher.PasswordHasher(newPassword);
            user.setUserPassword(hashedPassword);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Usuário não encontrado para redefinir a senha.");
        }
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
