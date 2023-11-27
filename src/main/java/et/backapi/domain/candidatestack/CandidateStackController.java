package et.backapi.domain.candidatestack;

import et.backapi.domain.candidate.Candidate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatestack")
public class CandidateStackController {
    private final CandidateStackRepository candidateStackRepository;

    public CandidateStackController(CandidateStackRepository candidateStackRepository) {
        this.candidateStackRepository = candidateStackRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createCandidateStack(@RequestBody CreateCandidateStackDTO candite){

        for (String stackname : candite.stackName()){

            Candidate candidate = new Candidate();
            candidate.setUserId(candite.candidate());


            CandidateStack candidateStack = new CandidateStack();
            candidateStack.setCandidate(candidate);
            candidateStack.setStackName(stackname);

            candidateStackRepository.save(candidateStack);
        }

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
