package softuni.javaweb.springproject.story.model.service;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.javaweb.springproject.comment.model.service.CommentServiceModel;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class StoryServiceModel {

    private String id;
    private String title;
    private List<String> pictures;
    private String description;
    private LocalDateTime createdOn;
    private String creator;
    private Sport sport;
    private Set<CommentServiceModel> comments;

    public StoryServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Length(min = 10, message = "Title must be at least 10 chars!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    @Length(min = 200, message = "Story description ust be descriptive!Min 200 chars! :)")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The date cannot be in the future!")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    @Length(min = 3, message = "Creator name must be at least 3 chars!")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @NotNull
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Set<CommentServiceModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentServiceModel> comments) {
        this.comments = comments;
    }
}
