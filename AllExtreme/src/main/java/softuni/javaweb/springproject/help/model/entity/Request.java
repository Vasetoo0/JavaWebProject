package softuni.javaweb.springproject.help.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "requests")
public class Request extends BaseEntity {

    private String name;
    private String email;
    private String subject;
    private String message;

    public Request() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(nullable = false)
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
