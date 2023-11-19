package et.backapi.domain.language;

import et.backapi.domain.language.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
