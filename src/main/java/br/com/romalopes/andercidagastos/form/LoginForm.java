package br.com.romalopes.andercidagastos.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	@Email(message="it is not a email format")
    @NotEmpty(message="email field is mandatory.")
	String email;
    @NotEmpty(message="password field is mandatory.")
	String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}


