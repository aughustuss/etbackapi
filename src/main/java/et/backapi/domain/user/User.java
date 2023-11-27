package et.backapi.domain.user;

import et.backapi.domain.address.Address;
import et.backapi.domain.candidate.Candidate;
import et.backapi.adapter.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
@Table(name = "et_users")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
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

    @OneToOne
    @JoinColumn(name = "userAddressId", referencedColumnName = "addressId")
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}