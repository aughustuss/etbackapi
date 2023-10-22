package et.backapi.Entities;


import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Enterprise extends User {
    private String enterpriseCnpj;
    private String enterpriseFantasyName;

    public String getEnterpriseCnpj() {
        return enterpriseCnpj;
    }

    public void setEnterpriseCnpj(String enterpriseCnpj) {
        this.enterpriseCnpj = enterpriseCnpj;
    }

    public String getEnterpriseFantasyName() {
        return enterpriseFantasyName;
    }

    public void setEnterpriseFantasyName(String enterpriseFantasyName) {
        this.enterpriseFantasyName = enterpriseFantasyName;
    }
}
