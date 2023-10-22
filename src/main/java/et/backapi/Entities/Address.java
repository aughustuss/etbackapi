package et.backapi.Entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Table(name = "et_address")
@Entity
@Component
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String addressStreet;
    private String addressAddress;
    private Integer addressNumber;
    private Number addressZipCode;
    private String addressNeighborhood;
    @OneToOne(mappedBy = "address")
    private User user;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressAddress() {
        return addressAddress;
    }

    public void setAddressAddress(String addressAddress) {
        this.addressAddress = addressAddress;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public Number getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(Number addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressNeighborhood() {
        return addressNeighborhood;
    }

    public void setAddressNeighborhood(String addressNeighborhood) {
        this.addressNeighborhood = addressNeighborhood;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
