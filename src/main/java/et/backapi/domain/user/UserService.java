package et.backapi.domain.user;

import et.backapi.encrypt.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UserService  implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByUserEmail(email);
    }



}
