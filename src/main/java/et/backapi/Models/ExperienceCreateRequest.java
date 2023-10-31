package et.backapi.Models;

import et.backapi.Models.Enums.ExperienceType;

import java.util.Date;

public class ExperienceCreateRequest {
    private String ecrRxperienceRole;
    private String ecrExperienceEnterprise;
    private String ecrExperienceDescription;
    private ExperienceType ecrExperienceType;
    private Date ecrExperienceStartDate;
    private Date ecrExperienceEndDate;

    public String getEcrRxperienceRole() {
        return ecrRxperienceRole;
    }

    public void setEcrRxperienceRole(String ecrRxperienceRole) {
        this.ecrRxperienceRole = ecrRxperienceRole;
    }

    public String getEcrExperienceEnterprise() {
        return ecrExperienceEnterprise;
    }

    public void setEcrExperienceEnterprise(String ecrExperienceEnterprise) {
        this.ecrExperienceEnterprise = ecrExperienceEnterprise;
    }

    public String getEcrExperienceDescription() {
        return ecrExperienceDescription;
    }

    public void setEcrExperienceDescription(String ecrExperienceDescription) {
        this.ecrExperienceDescription = ecrExperienceDescription;
    }

    public ExperienceType getEcrExperienceType() {
        return ecrExperienceType;
    }

    public void setEcrExperienceType(ExperienceType ecrExperienceType) {
        this.ecrExperienceType = ecrExperienceType;
    }

    public Date getEcrExperienceStartDate() {
        return ecrExperienceStartDate;
    }

    public void setEcrExperienceStartDate(Date ecrExperienceStartDate) {
        this.ecrExperienceStartDate = ecrExperienceStartDate;
    }

    public Date getEcrExperienceEndDate() {
        return ecrExperienceEndDate;
    }

    public void setEcrExperienceEndDate(Date ecrExperienceEndDate) {
        this.ecrExperienceEndDate = ecrExperienceEndDate;
    }
}
