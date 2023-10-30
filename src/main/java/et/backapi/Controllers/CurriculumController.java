package et.backapi.Controllers;

import et.backapi.Entities.Candidate;
import et.backapi.Entities.Course;
import et.backapi.Entities.Curriculum;
import et.backapi.Entities.User;
import et.backapi.Models.CurriculumCreateRequest;
import et.backapi.Repositories.CurriculumRepository;
import et.backapi.Repositories.UserRepository;
import org.apache.coyote.Response;
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
    private CurriculumRepository cr;

    private UserRepository ur;
    public CurriculumController(CurriculumRepository curriculumRepository, UserRepository userRepository) {this.cr = curriculumRepository; this.ur = userRepository;}

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCv(@PathVariable Long id){
        Optional<Curriculum> cvExists = cr.findById(id);
        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " nao encontrado");
        Curriculum cv = cvExists.get();
        return ResponseEntity.ok(cv);
    }

    @GetMapping("/getCourses/{id}")
    public ResponseEntity<?> getCvCourses(@PathVariable Long id){
        Optional<Curriculum> cvExists = cr.findById(id);
        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " nao encontrado");
        Curriculum cv = cvExists.get();
        List<Course> cvCourses = (List<Course>) cv.getCourses();

        if (cvCourses.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nao existem cursos para o curriculo de id " + id);
        return ResponseEntity.ok(cvCourses);
    }

//    @PostMapping("create/{id}")
//    public ResponseEntity<?> createCv(@PathVariable Long id, @RequestBody CurriculumCreateRequest ccr){
//        Optional<Candidate> cExists = ur.findById(id);
//        if(uExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o id " + id + " nao encontrado");
//        User u = uExists.get();
//        Curriculum cv = new Curriculum();
//        cv.setUserCurriculumRole(ccr.getCcrUserRole());
//        cv.setUserCurriculumSeniority(ccr.getCcrUserSeniority());
//    }
}
