package et.backapi.domain.candidate;

import et.backapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByUser(Optional<User> user);
}
