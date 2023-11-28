package et.backapi.domain.curriculum;

import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.course.Course;
import et.backapi.adapter.dto.CurriculumCreateRequestDto;
import et.backapi.domain.candidate.CandidateRepository;
import et.backapi.domain.user.User;
import et.backapi.domain.user.UserRepository;
import et.backapi.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("curriculum")
@RestController
public class CurriculumController {
    @Autowired
    private final CurriculumRepository cvr;
    private final CandidateRepository cr;

    private final UserRepository ur;

    private final TokenService tokenService;

    public CurriculumController(CurriculumRepository curriculumRepository, UserRepository userRepository, CandidateRepository candidateRepository, UserRepository ur, TokenService tokenService) {this.cvr = curriculumRepository; this.cr = candidateRepository;
        this.ur = ur;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCvs(){
        List<Curriculum> curriculumList = cvr.findAll();
        if (curriculumList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(curriculumList, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCv(@PathVariable Long id){
        Optional<Curriculum> cvExists = cvr.findById(id);
        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " nao encontrado");
        Curriculum cv = cvExists.get();
        return ResponseEntity.ok(cv);
    }

    @GetMapping("/getCourses/{id}")
    public ResponseEntity<?> getCvCourses(@PathVariable Long id){
        Optional<Curriculum> cvExists = cvr.findById(id);
        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " nao encontrado");
        Curriculum cv = cvExists.get();
        List<Course> cvCourses = (List<Course>) cv.getCourses();

        if (cvCourses.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao existem cursos para o curriculo de id " + id);
        return ResponseEntity.ok(cvCourses);
    }

    @PostMapping
    public ResponseEntity<?> createCv(@RequestHeader("Authorization") String token, @RequestBody CurriculumCreateRequestDto ccr){

        String result = token.replace("Bearer " , "");
        String id = tokenService.getSubject(result);
        Long userId = Long.parseLong(id);

        Optional<User> uExists = ur.findById(userId);
        if (uExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o ID " + userId + " não encontrado");
        }

        User u = uExists.get();

        Candidate c = cr.findByUser(u);

        Curriculum cv = new Curriculum();
        cv.setUserCurriculumRole(ccr.ccrUserRole());
        cv.setUserCurriculumSeniority(ccr.ccrUserSeniority());
        cv.setLinkGitHub(ccr.linkGitHub());
        cv.setLinkInstagram(ccr.linkInstagram());
        cv.setLinkPortifolio(ccr.linkPortifolio());
        cv.setLinkProfile(ccr.linkProfile());
        cv.setObjetivo(ccr.objetivo());
        cv.setCandidate(c);
        c.setCv(cv);
        cvr.save(cv);
        return ResponseEntity.status(HttpStatus.CREATED).body("Curriculo criado e associado ao usuario de id " + id);
    }
}
