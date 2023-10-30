package et.backapi.Controllers;

import et.backapi.Entities.User;
import et.backapi.Models.JwtToken;
import et.backapi.Models.UserCreateRequest;
import et.backapi.Repositories.UserRepository;
import et.backapi.Utils.HashPassword;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("users")
@RestController
public class UserController {
    private final UserRepository ur;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable Long id){
        Optional<User> uExists = ur.findById(id);
        if(uExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o id " + id + " nao foi encontrado");
        }
        User u = uExists.get();
        return ResponseEntity.ok(u);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest ucr){
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

        user.setUserEmailConfirmed(false);
        user.setUserCreatedOn(Date.from(Instant.now()));

        //Aqui seta um novo código pro atributo do token na tabela
        user.setUserToken(jt.getTokenCode());
        //E aqui adiciona a data dele, nao precisa mexer em nada aqui, só criar a logica de gerar o token e depois
        //fazer um set no tokenExpiration e no tokenCode do model JwtToken que tá na pasta Models
        user.setUserTokenExpiration(jt.getTokenExpiration());

        User createdUser = ur.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado");
    }

    @DeleteMapping("delete/{userId}")
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
}
