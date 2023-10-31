package et.backapi.Controllers;

import et.backapi.Entities.Course;
import et.backapi.Entities.Curriculum;
import et.backapi.Entities.User;
import et.backapi.Models.CourseCreateRequest;
import et.backapi.Repositories.CourseRepository;
import et.backapi.Repositories.CurriculumRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping("course")
@RestController
public class CourseController {
    private final CourseRepository cr;
    private final CurriculumRepository ccr;
    @Autowired
    public CourseController(CourseRepository courseRepository, CurriculumRepository curriculumRepository){this.cr = courseRepository; this.ccr = curriculumRepository;}

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        Optional<Course> cExists = cr.findById(id);
        if(cExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso com o id " + id + " nao existe");
        }
        Course c = cExists.get();
        return ResponseEntity.ok(c);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createCourse(@PathVariable Long id, @RequestBody CourseCreateRequest cscr){
        Optional<Curriculum> cvExists = ccr.findById(id);

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        Course c = new Course();
        c.setCourseName(cscr.getCscrCourseName());
        c.setCourseInstitution(cscr.getCscrCourseInstitution());
        c.setCourseStartDate(cscr.getCscrCourseStartDate());
        c.setCourseEndDate(cscr.getCscrCourseEndDate());
        cv.addCourse(c);
        ccr.save(cv);
        cr.save(c);
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
