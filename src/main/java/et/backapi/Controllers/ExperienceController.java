package et.backapi.Controllers;

import et.backapi.Entities.Curriculum;
import et.backapi.Entities.Experience;
import et.backapi.Models.ExperienceCreateRequest;
import et.backapi.Repositories.CurriculumRepository;
import et.backapi.Repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("experience")
@RestController
public class ExperienceController {
    private final ExperienceRepository er;
    private final CurriculumRepository ccr;
    @Autowired
    public ExperienceController(ExperienceRepository experienceRepository, CurriculumRepository curriculumRepository){
        this.er = experienceRepository;
        this.ccr = curriculumRepository;
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

    @PostMapping("create/{id}")
    public ResponseEntity<?> createExperience(@PathVariable Long id, @RequestBody ExperienceCreateRequest ecr){
        Optional<Curriculum> cvExists = ccr.findById(id);

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        Experience e = new Experience();
        e.setExperienceDescription(ecr.getEcrExperienceDescription());
        e.setExperienceEnterprise(ecr.getEcrExperienceEnterprise());
        e.setExperienceRole(ecr.getEcrRxperienceRole());
        e.setExperienceType(ecr.getEcrExperienceType());
        e.setExperienceStartDate(ecr.getEcrExperienceStartDate());
        e.setExperienceEndDate(ecr.getEcrExperienceEndDate());

        e.setCv(cv);
        cv.addExperience(e);

        ccr.save(cv);
        er.save(e);
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
