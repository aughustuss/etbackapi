package et.backapi.domain.experience;

import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.candidate.CandidateRepository;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.adapter.dto.ExperienceCreateRequestDto;
import et.backapi.domain.curriculum.CurriculumRepository;
import et.backapi.domain.user.User;
import et.backapi.domain.user.UserRepository;
import et.backapi.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("experience")
@RestController
public class ExperienceController {
    private final ExperienceRepository er;
    private final CurriculumRepository ccr;
    private final TokenService tokenService;

    private final UserRepository userRepository;

    private final CandidateRepository candidateRepository;

    @Autowired
    public ExperienceController(ExperienceRepository experienceRepository, CurriculumRepository curriculumRepository, TokenService tokenService, UserRepository userRepository, CandidateRepository candidateRepository){
        this.er = experienceRepository;
        this.ccr = curriculumRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getExperience(@PathVariable Long id){
        Optional<Experience> eExists = er.findById(id);
        if(eExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Experiencia com o id " + id + " nao existe");
        }
        Experience e = eExists.get();
        return ResponseEntity.ok(e);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> createExperience(@RequestHeader("Authorization") String token, @RequestBody ExperienceCreateRequestDto[] ecr){

        Long id = tokenService.extractId(token);

        Optional<User> user = userRepository.findById(id);

        Candidate candidate = candidateRepository.findByUser(user);

        Optional<Curriculum> cvExists = Optional.ofNullable(ccr.findByCandidate(candidate));

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        for (ExperienceCreateRequestDto experienceDTO : ecr){
            Experience e = new Experience();
            e.setExperienceDescription(experienceDTO.getEcrExperienceDescription());
            e.setExperienceEnterprise(experienceDTO.getEcrExperienceEnterprise());
            e.setExperienceRole(experienceDTO.getEcrRxperienceRole());
            e.setExperienceType(experienceDTO.getEcrExperienceType());
            e.setExperienceStartDate(experienceDTO.getEcrExperienceStartDate());
            e.setExperienceEndDate(experienceDTO.getEcrExperienceEndDate());
            e.setCv(cv);
            cv.addExperience(e);
            er.save(e);
        }

        ccr.save(cv);
        return ResponseEntity.status(HttpStatus.CREATED).body("Experiencia criada e associada ao curriculo de id" + id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeExperience(@PathVariable Long id) {
        Optional<Experience> eExists = er.findById(id);
        if (eExists.isPresent()) {
            Experience e = eExists.get();
            er.delete(e);
            return ResponseEntity.ok("Experiencia com id " + id + " deletada");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
