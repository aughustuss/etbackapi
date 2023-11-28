package et.backapi.domain.candidate;


import com.fasterxml.jackson.annotation.JsonBackReference;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.user.User;
import et.backapi.domain.candidatestack.CandidateStack;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "userID", referencedColumnName = "userId")
public class Candidate {

    @Id
    private String candidateCpf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidateCvId", referencedColumnName = "curriculumId")
    @JsonBackReference
    private Curriculum cv;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonBackReference
    private User user;

}
