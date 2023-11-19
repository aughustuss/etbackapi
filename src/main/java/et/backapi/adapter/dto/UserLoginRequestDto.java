package et.backapi.adapter.dto;

public class UserLoginRequestDto {
    private String ulEmail;
    private String ulPassword;

    public String getUlEmail() {
        return ulEmail;
    }

    public void setUlEmail(String ulEmail) {
        this.ulEmail = ulEmail;
    }

    public String getUlPassword() {
        return ulPassword;
    }

    public void setUlPassword(String ulPassword) {
        this.ulPassword = ulPassword;
    }
}
