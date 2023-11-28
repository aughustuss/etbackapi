package et.backapi.domain.curriculum;

import et.backapi.domain.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    Curriculum findByCandidate(Candidate candidate);
}
