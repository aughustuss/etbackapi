package et.backapi.domain.experience;

import et.backapi.domain.experience.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
