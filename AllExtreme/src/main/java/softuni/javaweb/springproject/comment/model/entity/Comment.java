package softuni.javaweb.springproject.comment.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.story.model.entity.Story;
import softuni.javaweb.springproject.user.model.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private UserEntity userEntity;
    private String description;
    private LocalDateTime createdOn;
    private Story story;

    public Comment() {
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    @ManyToOne
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
