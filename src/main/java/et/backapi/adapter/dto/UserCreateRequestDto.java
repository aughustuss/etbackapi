package et.backapi.adapter.dto;

import java.util.Date;

public class UserCreateRequestDto {
    private String ucFirstName;
    private String ucLastName;
    private Date ucBirthDate;
    private String ucEmail;
    private String ucPassword;
    private String userMobilePhone;

    public String getUserMobilePhone() {
        return userMobilePhone;
    }

    public void setUserMobilePhone(String userMobilePhone) {
        this.userMobilePhone = userMobilePhone;
    }

    public String getUcFirstName() {
        return ucFirstName;
    }

    public void setUcFirstName(String ucFirstName) {
        this.ucFirstName = ucFirstName;
    }

    public String getUcLastName() {
        return ucLastName;
    }

    public void setUcLastName(String ucLastName) {
        this.ucLastName = ucLastName;
    }

    public Date getUcBirthDate() {
        return ucBirthDate;
    }

    public void setUcBirthDate(Date ucBirthDate) {
        this.ucBirthDate = ucBirthDate;
    }

    public String getUcEmail() {
        return ucEmail;
    }

    public void setUcEmail(String ucEmail) {
        this.ucEmail = ucEmail;
    }

    public String getUcPassword() {
        return ucPassword;
    }

    public void setUcPassword(String ucPassword) {
        this.ucPassword = ucPassword;
    }
}
