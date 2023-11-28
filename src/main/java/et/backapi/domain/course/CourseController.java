package et.backapi.domain.course;

import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.candidate.CandidateRepository;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.adapter.dto.CourseCreateRequestDto;
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
@RequestMapping("course")
@RestController
public class CourseController {
    private final CourseRepository cr;
    private final CurriculumRepository ccr;
    private final TokenService tokenService;

    private final UserRepository userRepository;

    private final CandidateRepository candidateRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository, CurriculumRepository curriculumRepository, TokenService tokenService, UserRepository userRepository, CandidateRepository candidateRepository){
        this.cr = courseRepository;
        this.ccr = curriculumRepository;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        Optional<Course> cExists = cr.findById(id);
        if(cExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso com o id " + id + " nao existe");
        }
        Course c = cExists.get();
        return ResponseEntity.ok(c);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestHeader("Authorization") String token,@RequestBody CourseCreateRequestDto[] cscr){

        Long id = tokenService.extractId(token);

        Optional<User> user = userRepository.findById(id);

        Candidate candidate = candidateRepository.findByUser(user);

        Optional<Curriculum> cvExists = Optional.ofNullable(ccr.findByCandidate(candidate));

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        for (CourseCreateRequestDto courseDto : cscr){
            Course c = new Course();
            c.setCourseName(courseDto.getCscrCourseName());
            c.setCourseInstitution(courseDto.getCscrCourseInstitution());
            c.setCourseStartDate(courseDto.getCscrCourseStartDate());
            c.setCourseEndDate(courseDto.getCscrCourseEndDate());
            cv.addCourse(c);
            cr.save(c);
        }

        ccr.save(cv);
        return ResponseEntity.status(HttpStatus.CREATED).body("Curso criado e associado ao curriculo de id" + id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeCourse(@PathVariable Long id){
        Optional<Course> cExists = cr.findById(id);
        if(cExists.isPresent()){
            Course c = cExists.get();
            cr.delete(c);
            return ResponseEntity.ok("Curso com id " + id + " deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
