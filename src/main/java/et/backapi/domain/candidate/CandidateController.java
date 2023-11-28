package et.backapi.domain.candidate;

import et.backapi.adapter.dto.CandidateRegistrationRequestDto;
import et.backapi.adapter.dto.GetCandidatoResponseDTO;
import et.backapi.adapter.enums.UserType;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.curriculum.CurriculumRepository;
import et.backapi.domain.user.User;
import et.backapi.domain.user.UserRepository;
import et.backapi.infra.security.TokenService;
import lombok.extern.java.Log;
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

    private final CurriculumRepository ccr;

    private final TokenService tokenService;


    @Autowired
    public CandidateController(UserRepository userRepository, CandidateRepository candidateRepository, CurriculumRepository ccr, TokenService tokenService) {
        this.cr = candidateRepository;
        this.ur = userRepository;
        this.ccr = ccr;
        this.tokenService = tokenService;
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

        u.setUserRole(UserType.CANDIDATE);
        c.setUser(u);

        cr.save(c);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de usuário completo e feito.");
    }

    @GetMapping
    public ResponseEntity<?> listCandidate(@RequestHeader("Authorization") String token) {
        try {
            String result = token.replace("Bearer " , "");
            String id = tokenService.getSubject(result);
            Long userId = Long.parseLong(id);

            Optional<User> uExists = ur.findById(userId);
            if (uExists.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o ID " + userId + " não encontrado");
            }

            User u = uExists.get();
            Candidate candidate = cr.findByUser(u);
            System.out.println(candidate.getCv().getCurriculumId());
            Optional<Curriculum> curriculum = ccr.findById(candidate.getCv().getCurriculumId());

            if (candidate != null) {
                GetCandidatoResponseDTO candidatoResponseDTO = new GetCandidatoResponseDTO(u , candidate  , curriculum);
                return ResponseEntity.ok(candidatoResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado para o usuário com o ID " + userId);
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


}
