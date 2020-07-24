package softuni.javaweb.springproject.comment.model.view;

import softuni.javaweb.springproject.user.model.entity.UserEntity;

import java.time.LocalDateTime;

public class CommentViewModel {

    private String user;
    private String description;
    private LocalDateTime createdOn;

    public CommentViewModel() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
