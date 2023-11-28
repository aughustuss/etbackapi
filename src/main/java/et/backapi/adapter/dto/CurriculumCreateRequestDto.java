package et.backapi.adapter.dto;

import et.backapi.adapter.enums.UserSeniority;


public class CurriculumCreateRequestDto {
    String ccrUserRole;
    UserSeniority ccrUserSeniority;
    String linkPortifolio;
    String linkGitHub;
    String linkInstagram;
    String objetivo;
    String linkProfile;

    public CurriculumCreateRequestDto(String ccrUserRole, UserSeniority ccrUserSeniority, String linkPortifolio, String linkGitHub, String linkInstagram, String objetivo, String linkProfile) {
        this.ccrUserRole = ccrUserRole;
        this.ccrUserSeniority = ccrUserSeniority;
        this.linkPortifolio = linkPortifolio;
        this.linkGitHub = linkGitHub;
        this.linkInstagram = linkInstagram;
        this.objetivo = objetivo;
        this.linkProfile = linkProfile;
    }

    public CurriculumCreateRequestDto(){}

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
}
