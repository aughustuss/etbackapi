package et.backapi.domain.curriculum;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.candidatestack.CandidateStack;
import et.backapi.domain.course.Course;
import et.backapi.adapter.enums.UserSeniority;
import et.backapi.domain.experience.Experience;
import et.backapi.domain.language.Language;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    @JsonManagedReference
    private Candidate candidate;
    @OneToMany(mappedBy = "cv")
    @JsonManagedReference
    private List<Experience> experiences;
    @OneToMany(mappedBy = "cv")
    @JsonManagedReference
    private List<Course> courses;
    @OneToMany(mappedBy = "cv")
    @JsonManagedReference
    private List<Language> languages;
    @OneToMany(mappedBy = "cv")
    @JsonManagedReference
    private List<CandidateStack> candidateStacks;

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

    public void addCandidateStacks(CandidateStack candidateStack){
        if(candidateStacks == null) candidateStacks = new ArrayList<>();
        candidateStacks.add(candidateStack);
        candidateStack.setCv(this);
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

    public List<CandidateStack> getCandidateStacks (){return candidateStacks;}

    public void setUserCurriculumRole(String userCurriculumRole) {
        this.userCurriculumRole = userCurriculumRole;
    }

    public void setUserCurriculumSeniority(UserSeniority userCurriculumSeniority) {
        this.userCurriculumSeniority = userCurriculumSeniority;
    }
}
