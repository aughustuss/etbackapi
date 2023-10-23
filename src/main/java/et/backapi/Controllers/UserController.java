package et.backapi.Controllers;

import et.backapi.Email.EmailMessages;
import et.backapi.Email.EnviaEmailService;
import et.backapi.Entities.User;
import et.backapi.Models.JwtToken;
import et.backapi.Models.UserCreateRequest;
import et.backapi.Repositories.UserRepository;
import et.backapi.Utils.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {
    private final UserRepository ur;

    private EnviaEmailService enviaEmailService;
    private JwtToken jt = new JwtToken();
    @Autowired
    public UserController(UserRepository userRepository) {
        this.ur = userRepository;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = (List<User>) ur.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest ucr){
        User user = new User();
        user.setUserFirstName(ucr.getUcFirstName());
        user.setUserLastName(ucr.getUcLastName());
        user.setUserBirthDate(ucr.getUcBirthDate());
        user.setUserEmail(ucr.getUcEmail());
        HashPassword passwordHasher = new HashPassword();
        String hashedPassword = passwordHasher.PasswordHasher(ucr.getUcPassword());
        user.setUserPassword(hashedPassword);

        user.setUserEmailConfirmed(false);
        user.setUserCreatedOn(Date.from(Instant.now()));
        user.setUserToken(jt.getTokenCode());
        user.setUserTokenExpiration(jt.getTokenExpiration());
        User createdUser = ur.save(user);
        this.enviaEmailService.enviar(user.getUserEmail(),
                EmailMessages.createTitle(user),
                EmailMessages.messageToNewUser(user,user.getUserPassword()));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
