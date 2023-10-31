package et.backapi.Models;

import java.util.Date;

public class CourseCreateRequest {
    private String cscrCourseName;
    private String cscrCourseInstitution;
    private Date cscrCourseStartDate;
    private Date cscrCourseEndDate;

    public String getCscrCourseName() {
        return cscrCourseName;
    }

    public void setCscrCourseName(String cscrCourseName) {
        this.cscrCourseName = cscrCourseName;
    }

    public String getCscrCourseInstitution() {
        return cscrCourseInstitution;
    }

    public void setCscrCourseInstitution(String cscrCourseInstitution) {
        this.cscrCourseInstitution = cscrCourseInstitution;
    }

    public Date getCscrCourseStartDate() {
        return cscrCourseStartDate;
    }

    public void setCscrCourseStartDate(Date cscrCourseStartDate) {
        this.cscrCourseStartDate = cscrCourseStartDate;
    }

    public Date getCscrCourseEndDate() {
        return cscrCourseEndDate;
    }

    public void setCscrCourseEndDate(Date cscrCourseEndDate) {
        this.cscrCourseEndDate = cscrCourseEndDate;
    }
}
