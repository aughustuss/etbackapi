package et.backapi.domain.candidate;

import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.user.User;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByUser(User user);
}
