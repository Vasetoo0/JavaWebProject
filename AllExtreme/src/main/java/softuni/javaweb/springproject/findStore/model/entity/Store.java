package softuni.javaweb.springproject.findStore.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.enums.Sport;

import javax.persistence.*;

@Entity
@Table(name = "stores")
public class Store extends BaseEntity {

    private String picture;
    private String name;
    private String city;
    private String description;
    private String mapsLink;
    private String iFrameLink;
    private Sport sport;

    public Store() {
    }

    @Column(nullable = false)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "maps_link",nullable = false)
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

    @Enumerated
    @Column(nullable = false)
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Column(nullable = false,unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
