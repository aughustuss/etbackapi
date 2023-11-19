package et.backapi.domain.candidatestack;

import et.backapi.domain.candidate.Candidate;
import jakarta.persistence.*;

@Entity
@Table(name = "et_candidate_stack")
public class CandidateStack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stackName;

    @ManyToOne
    @JoinColumn(name = "candidateId", nullable = false)
    private Candidate candidate;

    public Long getId() {
        return id;
    }

    public String getStackName() {
        return stackName;
    }

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
