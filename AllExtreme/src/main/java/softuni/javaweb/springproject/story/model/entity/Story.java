package softuni.javaweb.springproject.story.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.comment.model.entity.Comment;
import softuni.javaweb.springproject.enums.Sport;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stories")
public class Story extends BaseEntity {

    private String title;
    private List<String> picturesLinks = new ArrayList<>();
    private String description;
    private LocalDateTime createdOn;
    private String creator;
    private Sport sport;
    private Set<Comment> comments;


    public Story() {
    }

    @Column(nullable = false,unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ElementCollection
    public List<String> getPicturesLinks() {
        return picturesLinks;
    }

    public void setPicturesLinks(List<String> picturesLinks) {
        this.picturesLinks = picturesLinks;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated
    @Column(nullable = false)
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Column(name = "created_on",nullable = false)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Column()
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @OneToMany(mappedBy = "story")
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
