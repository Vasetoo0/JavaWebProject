package softuni.javaweb.springproject.video.model;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.enums.Sport;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video extends BaseEntity {

    private String youTubeLink;
    private String description;
    private Sport sport;

    public Video() {
    }

    @Column(name = "you_tube_link",nullable = false)
    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
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
}
