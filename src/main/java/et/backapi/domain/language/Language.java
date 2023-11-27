package et.backapi.domain.language;

import com.fasterxml.jackson.annotation.JsonBackReference;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.adapter.enums.LanguageProficiency;
import jakarta.persistence.*;
@Entity
@Table(name = "et_curriculum_language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long languageId;
    private String languageName;
    private LanguageProficiency languageProficiency;
    @ManyToOne
    @JoinColumn(name = "curriculumId", nullable = false)
    @JsonBackReference
    private Curriculum cv;

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public LanguageProficiency getLanguageProficiency() {
        return languageProficiency;
    }

    public void setLanguageProficiency(LanguageProficiency languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    public Curriculum getCv() {
        return cv;
    }

    public void setCv(Curriculum cv) {
        this.cv = cv;
    }
}
