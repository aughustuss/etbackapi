package et.backapi.Models;

import et.backapi.Models.Enums.LanguageProficiency;

public class LanguageCreateRequest {
    private String lcrLanguageName;
    private LanguageProficiency lcrLanguageProficiency;

    public String getLcrLanguageName() {
        return lcrLanguageName;
    }

    public void setLcrLanguageName(String lcrLanguageName) {
        this.lcrLanguageName = lcrLanguageName;
    }

    public LanguageProficiency getLcrLanguageProficiency() {
        return lcrLanguageProficiency;
    }

    public void setLcrLanguageProficiency(LanguageProficiency lcrLanguageProficiency) {
        this.lcrLanguageProficiency = lcrLanguageProficiency;
    }
}
