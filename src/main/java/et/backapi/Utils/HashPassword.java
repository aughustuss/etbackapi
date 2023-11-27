package et.backapi.Utils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.lang.String;
@Service
public class HashPassword {
    public String PasswordHasher(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String oldPassword, String hashedPassword) {
        return BCrypt.checkpw(oldPassword, hashedPassword);
    }
}
