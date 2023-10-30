package et.backapi.Controllers;

import et.backapi.Entities.Course;
import et.backapi.Entities.Curriculum;
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

//    @GetMapping("/get/{idc}/{idcv}")
//    public ResponseEntity<?> getCurriculumCourse(@PathVariable Long idc, @PathVariable Long idcv){
//        Optional<Curriculum> cvExists = ccr.findById(idcv);
//        if(cvExists.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " +idcv+ " nao encontrado");
//        }
//        Curriculum cv = cvExists.get();
//
//        List<Course> cInCurriculum = (List<Course>) cv.getCourses();
//
//        Optional<Course> cExists = cInCurriculum.stream().filter(course -> course.getCourseId().equals(idc)).findFirst();
//
//        if(cExists.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cursos com o id " + idc + " nao encontrados");
//        }
//
//        Course c = cExists.get();
//
//        return ResponseEntity.ok(c);
//    }
}
