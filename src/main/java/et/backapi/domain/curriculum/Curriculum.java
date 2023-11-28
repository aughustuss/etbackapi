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


}
