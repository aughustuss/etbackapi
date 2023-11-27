package et.backapi.Controllers;

import et.backapi.Email.EmailMessages;
import et.backapi.Email.EnviaEmailService;
import et.backapi.Entities.User;
import et.backapi.Models.JwtToken;
import et.backapi.Models.UserCreateRequest;
import et.backapi.Repositories.UserRepository;
import et.backapi.Services.RandomStringService;
import et.backapi.Services.UserService;
import et.backapi.Utils.HashPassword;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("users")
@RestController
public class UserController {
    private final UserRepository ur;

    @Autowired
    private EnviaEmailService enviaEmailService;

    @Autowired
    private UserService userService;
    @Autowired
    private RandomStringService randomStringService;
    private JwtToken jt = new JwtToken();

    @Autowired
    public UserController(UserRepository userRepository) {
        this.ur = userRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = (List<User>) ur.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest ucr) {
        User user = new User();
        User userExists = ur.findByUserEmail(ucr.getUcEmail());
        if (userExists != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe");


        user.setUserFirstName(ucr.getUcFirstName());
        user.setUserLastName(ucr.getUcLastName());
        user.setUserBirthDate(ucr.getUcBirthDate());
        user.setUserEmail(ucr.getUcEmail());
        HashPassword passwordHasher = new HashPassword();
        String hashedPassword = passwordHasher.PasswordHasher(ucr.getUcPassword());
        user.setUserPassword(hashedPassword);
        String confirmEmailToken = randomStringService.generateRandomString(32);
        user.setUserConfirmEmailToken(confirmEmailToken);
        Instant now = Instant.now();
        Instant expiration = now.plus(30, ChronoUnit.MINUTES);
        user.setUserConfirmEmailTokenExpiration(Date.from(expiration));
        user.setUserEmailConfirmed(false);
        user.setUserCreatedOn(Date.from(Instant.now()));


        user.setUserToken(jt.getTokenCode());
        user.setUserTokenExpiration(jt.getTokenExpiration());

        User createdUser = ur.save(user);

        this.enviaEmailService.enviar(user.getUserEmail(),
                EmailMessages.createTitle(user),
                EmailMessages.messageToNewUser(user, user.getUserPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado");
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId) {
        Optional<User> userOptional = ur.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            ur.delete(user);
            return ResponseEntity.ok("Usuario com id " + userId + " deletado");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/confirmEmail")
    public ResponseEntity<String> confirmEmail(@RequestParam("userEmail") String userEmail, @RequestParam("confirmEmailToken") String confirmEmailToken) {
        if (userService.verificarTokenAutorizacao(userEmail, confirmEmailToken)) {
            User user = ur.findByUserEmail(userEmail);
            user.setUserEmailConfirmed(true);
            return ResponseEntity.ok("E-mail confirmado!!!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de confirmação de e-mail");
    }

    @PostMapping("/redefinirSenha")
    public ResponseEntity<String> redefinirSenha(@RequestParam String userEmail,
                                                 @RequestParam String oldPassword,
                                                 @RequestParam String newPassword) {

        try {
            if (userService.isOldPasswordCorrect(userEmail, oldPassword)) {
                userService.resetPassword(userEmail, newPassword);
                return ResponseEntity.ok("Senha redefinida com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha antiga incorreta.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao redefinir a senha: " + e.getMessage());
        }
    }
}

