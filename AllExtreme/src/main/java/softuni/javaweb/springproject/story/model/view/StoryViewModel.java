package softuni.javaweb.springproject.story.model.view;

import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.comment.model.view.CommentViewModel;
import softuni.javaweb.springproject.enums.Sport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class StoryViewModel {

    private String id;
    private String title;
    private List<String> pictures;
    private String description;
    private LocalDateTime createdOn;
    private String creator;
    private Sport sport;
    private Set<CommentViewModel> comments;

    public StoryViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Set<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentViewModel> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
