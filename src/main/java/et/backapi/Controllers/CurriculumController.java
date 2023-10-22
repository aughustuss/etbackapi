package et.backapi.Controllers;

import et.backapi.Repositories.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RequestMapping("curriculum")
@RestController
public class CurriculumController {
    @Autowired
    private CurriculumRepository cr;
    public CurriculumController(CurriculumRepository curriculumRepository) {this.cr = curriculumRepository;}

}
