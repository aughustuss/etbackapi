package et.backapi.domain.candidate;

import et.backapi.domain.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
