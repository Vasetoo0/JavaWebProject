package softuni.javaweb.springproject.comment.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.story.model.entity.Story;
import softuni.javaweb.springproject.user.model.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private String user;
    private String description;
    private LocalDateTime createdOn;

    public Comment() {
    }


    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "created_on",nullable = false)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column(nullable = false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
