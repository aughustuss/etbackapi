package et.backapi.domain.academicEducation;

import et.backapi.adapter.dto.CreateAcademicEductationDTO;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.curriculum.CurriculumRepository;
import et.backapi.infra.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("academicEducation")
@RestController
public class AcademicEducationController {

    private final AcademicEducationRepository aer;
    private final CurriculumRepository ccr;

    private final TokenService tokenService;

    public AcademicEducationController(AcademicEducationRepository aer, CurriculumRepository ccr, TokenService tokenService) {
        this.aer = aer;
        this.ccr = ccr;
        this.tokenService = tokenService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<String> createAcademicEducation(@RequestHeader("Authorization") String token, @RequestBody CreateAcademicEductationDTO[] createAcademicEductationDTO){

        Long id = tokenService.extractId(token);

        Optional<Curriculum> cvExists = ccr.findById(id);

        if(cvExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculo com o id " + id + " não encontrado");

        Curriculum cv = cvExists.get();

        for (CreateAcademicEductationDTO academicEductationDTO : createAcademicEductationDTO){
            AcademicEducation academicEducation = new AcademicEducation();

            academicEducation.setName(academicEductationDTO.name());
            academicEducation.setCourse(academicEductationDTO.course());
            academicEducation.setInstituicao(academicEductationDTO.instituicao());
            academicEducation.setBegin_data(academicEductationDTO.begin_data());
            academicEducation.setCompletion_date(academicEductationDTO.completion_date());

            cv.addAcademicEducation(academicEducation);
            aer.save(academicEducation);
        }

        ccr.save(cv);

        String responseMessage = "EXPERIENCIA ACADEMICA INSERIDA COM SUCESSO";
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);

    }
}
