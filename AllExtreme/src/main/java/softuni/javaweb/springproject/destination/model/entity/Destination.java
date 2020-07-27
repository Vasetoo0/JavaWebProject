package softuni.javaweb.springproject.destination.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.enums.Sport;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "destinations")
public class Destination extends BaseEntity {

    private String title;
    private String mapsLink;
    private String iFrameLink;
    private List<String> pictures;
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

    @Column(nullable = false,columnDefinition = "TEXT")
    public String getiIFrameLink() {
        return iFrameLink;
    }

    public void setiIFrameLink(String iFrame) {
        this.iFrameLink = iFrame;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> picturesLinks) {
        this.pictures = picturesLinks;
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
