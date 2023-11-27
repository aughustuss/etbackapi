package et.backapi.domain.candidatestack;

import et.backapi.adapter.dto.CreateCandidateStackDTO;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.curriculum.CurriculumRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidatestack")
public class CandidateStackController {
    private final CandidateStackRepository candidateStackRepository;
    private final CurriculumRepository curriculumRepository;

    public CandidateStackController(CandidateStackRepository candidateStackRepository, CurriculumRepository curriculumRepository) {
        this.candidateStackRepository = candidateStackRepository;
        this.curriculumRepository = curriculumRepository;
    }

    @Transactional
    @PostMapping("/{id}")
    public ResponseEntity<String> createCandidateStack(@PathVariable Long id, @RequestBody CreateCandidateStackDTO candidateStackDTO){
        Optional<Curriculum> cvExists = curriculumRepository.findById(id);

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        for (String stackname : candidateStackDTO.stackName()){
            CandidateStack candidateStack = new CandidateStack();
            candidateStack.setStackName(stackname);
            cv.addCandidateStacks(candidateStack);
            candidateStackRepository.save(candidateStack);
        }

        curriculumRepository.save(cv);

        String responseMessage = "STACK DO CANDIDATO INSERIDA COM SUCESSO";
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);

    }


    @GetMapping
    public ResponseEntity<List<CandidateStack>> listCandidateStack(){
        List<CandidateStack> candidateStacks = candidateStackRepository.findAll();
        return new ResponseEntity<>(candidateStacks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> listCandidateStack(@PathVariable("id") Long id){
        try {
            candidateStackRepository.deleteById(id);
            return new ResponseEntity<>("CandidateStack deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Falha ao deletar CandidateStack", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
