package et.backapi.domain.candidatestack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateStackRepository extends JpaRepository<CandidateStack, Long> {
}
