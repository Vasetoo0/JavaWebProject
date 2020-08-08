package softuni.javaweb.springproject.offer.model.service;

import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OfferServiceModel {

    private String id;
    private String title;
    private String model;
    private List<String> pictures;
    private String productCondition;
    private String telContact;
    private BigDecimal price;
    private LocalDateTime createdOn = LocalDateTime.now();
    private String description;
    private Boolean enabled = false;
    private Sport sport;
    private String creator;

    public OfferServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTelContact() {
        return telContact;
    }

    public void setTelContact(String telContact) {
        this.telContact = telContact;
    }
}
