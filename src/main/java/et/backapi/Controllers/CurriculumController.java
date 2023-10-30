package et.backapi.Controllers;

import et.backapi.Entities.Curriculum;
import et.backapi.Repositories.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("curriculum")
@RestController
public class CurriculumController {
    @Autowired
    private CurriculumRepository cr;
    public CurriculumController(CurriculumRepository curriculumRepository) {this.cr = curriculumRepository;}

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCv(@PathVariable Long id){
        Optional<Curriculum> cvExists = cr.findById(id);
        if(cvExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " nao encontrado");
        }
        Curriculum cv = cvExists.get();
        return ResponseEntity.ok(cv);
    }

//    @GetMapping("/getCourses/{id}")
//    public ResponseEntity<?> getCvCourses(@PathVariable Long id){
//
//    }
}
