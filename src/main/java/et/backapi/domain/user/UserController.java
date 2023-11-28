package et.backapi.domain.user;

import et.backapi.adapter.dto.LoginDTO;
import et.backapi.adapter.email.EmailMessages;
import et.backapi.adapter.email.EnviaEmailService;
import et.backapi.infra.security.TokenJWTData;
import et.backapi.infra.security.TokenService;
import et.backapi.encrypt.jwt.JwtToken;
import et.backapi.adapter.dto.UserCreateRequestDto;
import et.backapi.domain.candidate.CandidateRepository;
import et.backapi.encrypt.RandomString;
import et.backapi.encrypt.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("user")
@RestController
public class UserController {
    private final UserRepository ur;
    @Autowired
    private EnviaEmailService enviaEmailService;
    @Autowired
    private RandomString randomStringService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    private JwtToken jt = new JwtToken();
    @Autowired
    public UserController(UserRepository userRepository) {
        this.ur = userRepository;
    }

    @Autowired
    private UserService userService;


    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = (List<User>) ur.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        Optional<User> uExists = ur.findById(id);
        if(uExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o id " + id + " nao foi encontrado");
        }
        User u = uExists.get();
        return ResponseEntity.ok(u);
    }
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequestDto ucr){
        User user = new User();
        User userExists = ur.findByUserEmail(ucr.getUcEmail());
        if(userExists != null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe");


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

        //Aqui seta um novo código pro atributo do token na tabela
        user.setUserToken(jt.getTokenCode());
        //E aqui adiciona a data dele, nao precisa mexer em nada aqui, só criar a logica de gerar o token e depois
        //fazer um set no tokenExpiration e no tokenCode do model JwtToken que tá na pasta Models
        user.setUserTokenExpiration(jt.getTokenExpiration());

        User createdUser = ur.save(user);

        this.enviaEmailService.enviar(user.getUserEmail(),
                EmailMessages.createTitle(user),
                EmailMessages.messageToNewUser(user,user.getUserPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        Optional<User> userOptional = ur.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            ur.delete(user);
            return ResponseEntity.ok("Usuario com id " + userId + " deletado");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody LoginDTO data){
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(data.ucEmail() , data.ucPassword());
            Authentication authentication = this.manager.authenticate(usernamePasswordAuthenticationToken);
            var user = (User) authentication.getPrincipal();
            var tokenJWT = tokenService.gerarToken(user);
            return ResponseEntity.ok(new TokenJWTData(tokenJWT));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
