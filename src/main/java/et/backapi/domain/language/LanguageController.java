package et.backapi.domain.language;

import et.backapi.domain.curriculum.Curriculum;
import et.backapi.adapter.dto.LanguageCreateRequestDto;
import et.backapi.domain.curriculum.CurriculumRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RequestMapping("language")
@RestController
public class LanguageController {
    private final LanguageRepository lr;
    private final CurriculumRepository cr;

    public LanguageController(LanguageRepository languageRepository, CurriculumRepository curriculumRepository){
        this.lr = languageRepository;
        this.cr = curriculumRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLanguage(@PathVariable Long id){
        Optional<Language> lExists = lr.findById(id);
        if(lExists.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idioma com o id " + id + " nao existe");
        }
        Language l = lExists.get();
        return ResponseEntity.ok(l);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createCourse(@PathVariable Long id, @RequestBody LanguageCreateRequestDto[] lcr){
        Optional<Curriculum> cvExists = cr.findById(id);

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        Language l = new Language();

        for(LanguageCreateRequestDto languageDTO : lcr){
            l.setLanguageName(languageDTO.getLcrLanguageName());
            l.setLanguageProficiency(languageDTO.getLcrLanguageProficiency());
            cv.addLanguage(l);
            lr.save(l);
        }

        cr.save(cv);
        return ResponseEntity.status(HttpStatus.CREATED).body("Idioma criado e associado ao curriculo de id" + id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeLanguage(@PathVariable Long id) {
        Optional<Language> lExists = lr.findById(id);
        if (lExists.isPresent()) {
            Language l = lExists.get();
            lr.delete(l);
            return ResponseEntity.ok("Idioma com id " + id + " deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
