package et.backapi.encrypt.jwt;

import java.util.Date;

public class JwtToken {
    private String tokenCode;
    private Date tokenExpiration;

    public JwtToken(String createdTokenCode, Date createdTokenExpiration){
        this.tokenCode = createdTokenCode;
        this.tokenExpiration = createdTokenExpiration;
    }

    public JwtToken(){

    }
    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public Date getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Date tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }
}
