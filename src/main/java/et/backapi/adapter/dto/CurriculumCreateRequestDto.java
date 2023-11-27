package et.backapi.adapter.dto;

import et.backapi.adapter.enums.UserSeniority;


public class CurriculumCreateRequestDto {
    private String ccrUserRole;
    private UserSeniority ccrUserSeniority;

    private String linkPortifolio;
    private String linkGitHub;
    private String linkInstagram;

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
