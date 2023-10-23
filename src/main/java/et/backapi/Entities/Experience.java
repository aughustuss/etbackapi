package et.backapi.Entities;

import et.backapi.Models.Enums.ExperienceType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "et_curriculum_experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long experienceId;
    private String experienceRole;
    private String experienceEnterprise;
    private String experienceDescription;
    private ExperienceType experienceType;
    private Date experienceStartDate;
    private Date experienceEndDate;
    @ManyToOne
    @JoinColumn(name = "curriculumId", nullable = false)
    private Curriculum cv;

    public Experience() {
    }

    public Long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Long experienceId) {
        this.experienceId = experienceId;
    }

    public String getExperienceRole() {
        return experienceRole;
    }

    public void setExperienceRole(String experienceRole) {
        this.experienceRole = experienceRole;
    }

    public String getExperienceEnterprise() {
        return experienceEnterprise;
    }

    public void setExperienceEnterprise(String experienceEnterprise) {
        this.experienceEnterprise = experienceEnterprise;
    }

    public String getExperienceDescription() {
        return experienceDescription;
    }

    public void setExperienceDescription(String experienceDescription) {
        this.experienceDescription = experienceDescription;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }

    public Date getExperienceStartDate() {
        return experienceStartDate;
    }

    public void setExperienceStartDate(Date experienceStartDate) {
        this.experienceStartDate = experienceStartDate;
    }

    public Date getExperienceEndDate() {
        return experienceEndDate;
    }

    public void setExperienceEndDate(Date experienceEndDate) {
        this.experienceEndDate = experienceEndDate;
    }

    public Curriculum getCv() {
        return cv;
    }

    public void setCv(Curriculum cv) {
        this.cv = cv;
    }
}
