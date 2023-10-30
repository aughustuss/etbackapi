package et.backapi.Entities;

import et.backapi.Models.Enums.UserSeniority;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
    private List<Experience> experiences;
    @OneToMany(mappedBy = "cv")
    private List<Course> courses;
    @OneToMany(mappedBy = "cv")
    private List<Language> languages;

    public void addCourse(Course course){
        if(courses == null) courses = new ArrayList<>();
        courses.add(course);
        course.setCv(this);
    }

    public void addExperience(Experience experience){
        if(experiences == null) experiences = new ArrayList<>();
        experiences.add(experience);
        experience.setCv(this);
    }

    public void addLanguage(Language language){
        if(languages == null) languages = new ArrayList<>();
        languages.add(language);
        language.setCv(this);
    }
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

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setUserCurriculumRole(String userCurriculumRole) {
        this.userCurriculumRole = userCurriculumRole;
    }

    public void setUserCurriculumSeniority(UserSeniority userCurriculumSeniority) {
        this.userCurriculumSeniority = userCurriculumSeniority;
    }
}
