package softuni.javaweb.springproject.comment.model.service;

import softuni.javaweb.springproject.story.model.service.StoryServiceModel;

import java.time.LocalDateTime;

public class CommentServiceModel {

    private String username;
    private String description;
    private LocalDateTime createdOn;

    public CommentServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
