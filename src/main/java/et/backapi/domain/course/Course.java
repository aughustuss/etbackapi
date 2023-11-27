package et.backapi.domain.course;

import com.fasterxml.jackson.annotation.JsonBackReference;
import et.backapi.domain.curriculum.Curriculum;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "et_curriculum_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;
    private String courseName;
    private String courseInstitution;
    private Date courseStartDate;
    private Date courseEndDate;
    @ManyToOne
    @JoinColumn(name = "curriculumId", nullable = false)
    @JsonBackReference
    private Curriculum cv;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInstitution() {
        return courseInstitution;
    }

    public void setCourseInstitution(String courseInstitution) {
        this.courseInstitution = courseInstitution;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public Curriculum getCv() {
        return cv;
    }

    public void setCv(Curriculum cv) {
        this.cv = cv;
    }


}
