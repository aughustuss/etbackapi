package et.backapi.domain.candidate;

import et.backapi.adapter.dto.CandidateRegistrationRequestDto;
import et.backapi.adapter.enums.UserType;
import et.backapi.domain.user.User;
import et.backapi.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RequestMapping("candidate")
@RestController
public class CandidateController {
    private final CandidateRepository cr;
    private final UserRepository ur;
    @Autowired
    public CandidateController(UserRepository userRepository, CandidateRepository candidateRepository) {
        this.cr = candidateRepository;
        this.ur = userRepository;
    }
    @PostMapping("{userId}")
    public ResponseEntity<?> completeUserRegistration(@PathVariable Long userId, @RequestBody CandidateRegistrationRequestDto crr) {
        Optional<User> uExists = ur.findById(userId);
        if (uExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o ID " + userId + " não encontrado");
        }
        User u = uExists.get();

        Candidate c = new Candidate();
        c.setCandidateCpf(crr.getCrrCandidateCpf());
        c.setCandidateGithubLink(crr.getCrrCandidateGithubLink());
        c.setCandidateInstagramLink(crr.getCrrCandidateInstagramLink());

        // Verifique o candidato antes de associá-lo ao usuário
        System.out.println("Candidate before association: " + c);

        u.setCandidate(c);
        u.setUserRole(UserType.CANDIDATE);

        // Verifique novamente o candidato após a associação
        System.out.println("Candidate after association: " + c);

        cr.save(c);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de usuário completo e feito.");
    }
}
