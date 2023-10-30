package et.backapi.Entities;

import et.backapi.Models.Enums.UserSeniority;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Table(name = "et_curriculum")
@Entity
@Component
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long curriculumId;

    private String userCurriculumRole;
    private UserSeniority userCurriculumSeniority;
    @OneToOne(mappedBy = "cv")
    private Candidate candidate;
    @OneToMany(mappedBy = "cv")
    private Set<Experience> experiences;
    @OneToMany(mappedBy = "cv")
    private Set<Course> courses;
    @OneToMany(mappedBy = "cv")
    private Set<Language> languages;

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getUserCurriculumRole() {
        return userCurriculumRole;
    }

    public UserSeniority getUserCurriculumSeniority() {
        return userCurriculumSeniority;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Set<Language> getLanguages() {
        return languages;
    }
}
