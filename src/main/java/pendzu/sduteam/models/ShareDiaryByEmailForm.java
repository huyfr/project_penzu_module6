package pendzu.sduteam.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ShareDiaryByEmailForm {

    @Email
    @NotEmpty
    private String email;

    public ShareDiaryByEmailForm(@Email @NotEmpty String email) {
        this.email = email;
    }

    public ShareDiaryByEmailForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
