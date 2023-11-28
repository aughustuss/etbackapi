package et.backapi.domain.curriculum;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import et.backapi.domain.academicEducation.AcademicEducation;
import et.backapi.domain.candidate.Candidate;
import et.backapi.domain.candidatestack.CandidateStack;
import et.backapi.domain.course.Course;
import et.backapi.adapter.enums.UserSeniority;
import et.backapi.domain.experience.Experience;
import et.backapi.domain.language.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Table(name = "et_curriculum")
@Entity
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long curriculumId;

    private String userCurriculumRole;
    private UserSeniority userCurriculumSeniority;

    private String linkPortifolio;
    private String linkGitHub;
    private String linkInstagram;

    private String objetivo;

    private String linkProfile;

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

    @OneToMany(mappedBy = "cv")
    @JsonManagedReference
    private List<AcademicEducation> academicEducations;

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

    public void addAcademicEducation(AcademicEducation academicEducation){
        if (academicEducations == null) academicEducations = new ArrayList<>();
        academicEducations.add(academicEducation);
        academicEducation.setCv(this);
    }

    public List<CandidateStack> getCandidateStacks() {
        return candidateStacks;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getUserCurriculumRole() {
        return userCurriculumRole;
    }

    public void setUserCurriculumRole(String userCurriculumRole) {
        this.userCurriculumRole = userCurriculumRole;
    }

    public UserSeniority getUserCurriculumSeniority() {
        return userCurriculumSeniority;
    }

    public void setUserCurriculumSeniority(UserSeniority userCurriculumSeniority) {
        this.userCurriculumSeniority = userCurriculumSeniority;
    }

    public String getLinkPortifolio() {
        return linkPortifolio;
    }

    public void setLinkPortifolio(String linkPortifolio) {
        this.linkPortifolio = linkPortifolio;
    }

    public String getLinkGitHub() {
        return linkGitHub;
    }

    public void setLinkGitHub(String linkGitHub) {
        this.linkGitHub = linkGitHub;
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getLinkProfile() {
        return linkProfile;
    }

    public void setLinkProfile(String linkProfile) {
        this.linkProfile = linkProfile;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public void setCandidateStacks(List<CandidateStack> candidateStacks) {
        this.candidateStacks = candidateStacks;
    }

    public List<AcademicEducation> getAcademicEducations() {
        return academicEducations;
    }

    public void setAcademicEducations(List<AcademicEducation> academicEducations) {
        this.academicEducations = academicEducations;
    }
}
