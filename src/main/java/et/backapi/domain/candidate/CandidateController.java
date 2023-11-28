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

import java.util.List;
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
    public ResponseEntity<?> completeUserRegistration(@RequestHeader("Authorization") String token, @RequestBody CandidateRegistrationRequestDto crr) {
        Optional<User> uExists = ur.findById(tokenService.extractId(token));
        if (uExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        User u = uExists.get();

        Candidate c = new Candidate();
        c.setCandidateCpf(crr.crrCandidateCpf());

        u.setUserRole(UserType.CANDIDATE);
        c.setUser(u);

        cr.save(c);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro de usuário completo e feito.");
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAllCandidates(){
        List<Candidate> candidates = cr.findAll();
        if (candidates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> listCandidate(@RequestHeader("Authorization") String token) {
        try {
            Optional<User> uExists = ur.findById(tokenService.extractId(token));
            if (uExists.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }

            User u = uExists.get();
            Optional<Candidate> candidateOptional = Optional.ofNullable(cr.findByUser(u));

            if(candidateOptional.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado para o usuário");
            }

            Candidate candidate = candidateOptional.get();

            if (candidate.getCv() == null || candidate.getCv().getCurriculumId() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currículo não encontrado para o candidato");
            }

            Optional<Curriculum> curriculumOptional = ccr.findById(candidate.getCv().getCurriculumId());

            if (curriculumOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currículo não encontrado");
            }

            Curriculum curriculum = curriculumOptional.get();

            GetCandidatoResponseDTO candidatoResponseDTO = new GetCandidatoResponseDTO(u, candidate, Optional.of(curriculum));
            return ResponseEntity.ok(candidatoResponseDTO);

        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }


}
