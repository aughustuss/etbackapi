package et.backapi.domain.address;

import et.backapi.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Table(name = "et_address")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long addressId;
    private String addressStreet;
    private String addressAddress;
    private Integer addressNumber;
    private Number addressZipCode;
    private String addressNeighborhood;
    @OneToOne(mappedBy = "address")
    private User user;

}
