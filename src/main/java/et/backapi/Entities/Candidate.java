package et.backapi.Entities;

;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class Candidate extends User {
    private String candidateCpf;
    private List<String> candidateStack;
    private String candidateInstagramLink;
    private String candidateGithubLink;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidateCvId", referencedColumnName = "curriculumId")
    private Curriculum cv;

    public String getCandidateCpf() {
        return candidateCpf;
    }

    public void setCandidateCpf(String candidateCpf) {
        this.candidateCpf = candidateCpf;
    }

    public List<String> getCandidateStack() {
        return candidateStack;
    }

    public void setCandidateStack(List<String> candidateStack) {
        this.candidateStack = candidateStack;
    }

    public String getCandidateInstagramLink() {
        return candidateInstagramLink;
    }

    public void setCandidateInstagramLink(String candidateInstagramLink) {
        this.candidateInstagramLink = candidateInstagramLink;
    }

    public String getCandidateGithubLink() {
        return candidateGithubLink;
    }

    public void setCandidateGithubLink(String candidateGithubLink) {
        this.candidateGithubLink = candidateGithubLink;
    }

    public Curriculum getCv() {
        return cv;
    }

    public void setCv(Curriculum cv) {
        this.cv = cv;
    }
}
