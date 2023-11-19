package et.backapi.domain.user;

import et.backapi.domain.address.Address;
import et.backapi.domain.candidate.Candidate;
import et.backapi.adapter.enums.UserType;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Date;
@Table(name = "et_users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userMobilePhone;
    private String userPassword;
    private Boolean userEmailConfirmed = false;
    private String userToken;
    private Date userTokenExpiration;
    private String userConfirmEmailToken;
    private Date userConfirmEmailTokenExpiration;
    private Date userBirthDate;
    private Date userCreatedOn;
    private UserType userRole;
    @OneToOne(mappedBy = "user")
    private Candidate candidate;
    @OneToOne
    @JoinColumn(name = "userAddressId", referencedColumnName = "addressId")
    private Address address;
    public User(String userEmail, String userFirstName, String userLastName, String userMobilePhone, String userPassword, String userToken, Date userTokenExpiration, String userConfirmEmailToken, Date userConfirmEmailTokenExpiration, Date userBirthDate) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userMobilePhone = userMobilePhone;
        this.userPassword = userPassword;
        this.userToken = userToken;
        this.userTokenExpiration = userTokenExpiration;
        this.userConfirmEmailToken = userConfirmEmailToken;
        this.userConfirmEmailTokenExpiration = userConfirmEmailTokenExpiration;
        this.userBirthDate = userBirthDate;
    }

    public User(){}
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMobilePhone() {
        return userMobilePhone;
    }

    public void setUserMobilePhone(String userMobilePhone) {
        this.userMobilePhone = userMobilePhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getUserEmailConfirmed() {
        return userEmailConfirmed;
    }

    public void setUserEmailConfirmed(Boolean userEmailConfirmed) {
        this.userEmailConfirmed = userEmailConfirmed;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Date getUserTokenExpiration() {
        return userTokenExpiration;
    }

    public void setUserTokenExpiration(Date userTokenExpiration) {
        this.userTokenExpiration = userTokenExpiration;
    }

    public String getUserConfirmEmailToken() {
        return userConfirmEmailToken;
    }

    public void setUserConfirmEmailToken(String userConfirmEmailToken) {
        this.userConfirmEmailToken = userConfirmEmailToken;
    }

    public Date getUserConfirmEmailTokenExpiration() {
        return userConfirmEmailTokenExpiration;
    }

    public void setUserConfirmEmailTokenExpiration(Date userConfirmEmailTokenExpiration) {
        this.userConfirmEmailTokenExpiration = userConfirmEmailTokenExpiration;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public Date getUserCreatedOn() {
        return userCreatedOn;
    }

    public void setUserCreatedOn(Date userCreatedOn) {
        this.userCreatedOn = userCreatedOn;
    }

    public UserType getUserRole() {
        return userRole;
    }

    public void setUserRole(UserType userRole) {
        this.userRole = userRole;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
