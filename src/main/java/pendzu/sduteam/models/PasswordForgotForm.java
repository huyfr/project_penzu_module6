package pendzu.sduteam.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordForgotForm {
        @Email
        @NotEmpty
        private String email;

    public PasswordForgotForm(@Email @NotEmpty String email) {
        this.email = email;
    }

    public PasswordForgotForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
