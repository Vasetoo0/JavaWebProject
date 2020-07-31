package softuni.javaweb.springproject.findStore.model.service;

import softuni.javaweb.springproject.enums.Sport;

public class StoreServiceModel {

    private String id;
    private String picture;
    private String name;
    private String city;
    private String description;
    private String mapsLink;
    private String iFrameLink;
    private Sport sport;

    public StoreServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    public String getiFrameLink() {
        return iFrameLink;
    }

    public void setiFrameLink(String iFrameLink) {
        this.iFrameLink = iFrameLink;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
