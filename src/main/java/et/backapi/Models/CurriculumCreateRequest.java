package et.backapi.Models;

import et.backapi.Models.Enums.UserSeniority;


public class CurriculumCreateRequest {
    private Long ccrUserId;
    private String ccrUserRole;
    private UserSeniority ccrUserSeniority;

    public Long getCcrUserId() {
        return ccrUserId;
    }

    public void setCcrUserId(Long ccrUserId) {
        this.ccrUserId = ccrUserId;
    }

    public String getCcrUserRole() {
        return ccrUserRole;
    }

    public void setCcrUserRole(String ccrUserRole) {
        this.ccrUserRole = ccrUserRole;
    }

    public UserSeniority getCcrUserSeniority() {
        return ccrUserSeniority;
    }

    public void setCcrUserSeniority(UserSeniority ccrUserSeniority) {
        this.ccrUserSeniority = ccrUserSeniority;
    }
}
