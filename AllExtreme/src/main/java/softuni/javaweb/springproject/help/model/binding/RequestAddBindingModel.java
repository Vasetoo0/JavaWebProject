package softuni.javaweb.springproject.help.model.binding;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class RequestAddBindingModel {

    private String name;
    private String email;
    private String subject;
    private String message;

    public RequestAddBindingModel() {
    }

    @Length(min = 3, message = "Name must be at least 3 chars long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 5, message = "Subject must be at least 5 chars long!")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Length(min = 40, message = "Message must be at least 40 chars long!")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
