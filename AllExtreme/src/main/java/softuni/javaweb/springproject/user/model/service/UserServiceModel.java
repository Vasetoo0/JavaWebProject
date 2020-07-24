package softuni.javaweb.springproject.user.model.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UserServiceModel {

    private String id;
    private String username;
    private String password;
    private String email;

    public UserServiceModel() {
    }

    @Length(min = 3,max = 15,message = "Username length must be in bounds 3 - 15 chars!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Length(min = 5,message = "Password length must be min 5  chars!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Enter valid email!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
