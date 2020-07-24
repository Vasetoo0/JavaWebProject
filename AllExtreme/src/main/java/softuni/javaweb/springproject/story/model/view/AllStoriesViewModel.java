package softuni.javaweb.springproject.story.model.view;

import softuni.javaweb.springproject.comment.model.view.CommentViewModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AllStoriesViewModel {

    private String title;
    private List<String> picturesLinks = new ArrayList<>();
    private String description;
    private LocalDateTime createdOn;
    private String creator;
    private Set<CommentViewModel> comments;

    public AllStoriesViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPicturesLinks() {
        return picturesLinks;
    }

    public void setPicturesLinks(List<String> picturesLinks) {
        this.picturesLinks = picturesLinks;
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

    public Set<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(Set<CommentViewModel> comments) {
        this.comments = comments;
    }
}
