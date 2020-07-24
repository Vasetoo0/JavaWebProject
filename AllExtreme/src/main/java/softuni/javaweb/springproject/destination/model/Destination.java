package softuni.javaweb.springproject.destination.model;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.enums.Sport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destination extends BaseEntity {

    private String title;
    private String mapsLink;
    private String iFrame;
    private List<String> picturesLinks = new ArrayList<>();
    private String description;
    private Sport sport;

    public Destination() {
    }

    @Column(nullable = false,unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    @Column(nullable = false)
    public String getiFrame() {
        return iFrame;
    }

    public void setiFrame(String iFrame) {
        this.iFrame = iFrame;
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
}
