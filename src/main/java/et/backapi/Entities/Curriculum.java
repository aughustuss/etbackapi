package et.backapi.Entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Table(name = "et_curriculum")
@Entity
@Component
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long curriculumId;
    @OneToOne(mappedBy = "cv")
    private Candidate candidate;

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
