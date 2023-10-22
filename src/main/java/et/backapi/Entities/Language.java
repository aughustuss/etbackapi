package et.backapi.Entities;

import et.backapi.Models.Enums.LanguageProficiency;
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
