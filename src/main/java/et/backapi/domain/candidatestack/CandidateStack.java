package et.backapi.domain.candidatestack;

import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.curriculum.Curriculum;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "et_candidate_stack")
public class CandidateStack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stackName;

    @ManyToOne
    @JoinColumn(name = "curriculumId", nullable = false)
    private Curriculum cv;

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public void setCv(Curriculum cv) {
        this.cv = cv;
    }
}
