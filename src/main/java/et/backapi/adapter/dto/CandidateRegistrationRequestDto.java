package et.backapi.adapter.dto;

public class CandidateRegistrationRequestDto {
    private String crrCandidateCpf;
    private String crrCandidateInstagramLink;
    private String crrCandidateGithubLink;

    public String getCrrCandidateCpf() {
        return crrCandidateCpf;
    }

    public void setCrrCandidateCpf(String crrCandidateCpf) {
        this.crrCandidateCpf = crrCandidateCpf;
    }

    public String getCrrCandidateInstagramLink() {
        return crrCandidateInstagramLink;
    }

    public void setCrrCandidateInstagramLink(String crrCandidateInstagramLink) {
        this.crrCandidateInstagramLink = crrCandidateInstagramLink;
    }

    public String getCrrCandidateGithubLink() {
        return crrCandidateGithubLink;
    }

    public void setCrrCandidateGithubLink(String crrCandidateGithubLink) {
        this.crrCandidateGithubLink = crrCandidateGithubLink;
    }
}
