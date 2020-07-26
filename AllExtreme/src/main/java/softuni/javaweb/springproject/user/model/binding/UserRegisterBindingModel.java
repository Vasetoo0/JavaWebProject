package softuni.javaweb.springproject.user.model.binding;


import org.hibernate.validator.constraints.Length;
import softuni.javaweb.springproject.common.validators.FieldMatch;

import javax.validation.constraints.Email;

@FieldMatch(first = "password",
        second = "confirmPassword",
        message = "The passwords do not match")
public class UserRegisterBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBindingModel() {
    }

    @Length(min = 3,max = 15,message = "Username length must be between 3 and 15 chars!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 5,message = "Password length must be minimum 5  chars!")
    //TODO: Set password pattern!
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Email(message = "Enter valid email!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
