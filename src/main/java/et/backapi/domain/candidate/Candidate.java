package et.backapi.domain.candidate;


import com.fasterxml.jackson.annotation.JsonBackReference;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.user.User;
import et.backapi.domain.candidatestack.CandidateStack;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
public class Candidate extends User {
    private String candidateCpf;
    private String candidateInstagramLink;
    private String candidateGithubLink;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CandidateStack> candidateStack;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidateCvId", referencedColumnName = "curriculumId")
    @JsonBackReference
    private Curriculum cv;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    public String getCandidateCpf() {
        return candidateCpf;
    }

    public void setCandidateCpf(String candidateCpf) {
        this.candidateCpf = candidateCpf;
    }

    public List<CandidateStack> getCandidateStack() {
        return candidateStack;
    }

    public void setCandidateStack(List<CandidateStack> candidateStack) {
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
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
