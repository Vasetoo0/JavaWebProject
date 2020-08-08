package softuni.javaweb.springproject.offer.model.entity;
import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.enums.Sport;
import softuni.javaweb.springproject.user.model.entity.UserEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private String title;
    private String model;
    private List<String> pictures;
    private String productCondition;
    private BigDecimal price;
    private String telContact;
    private LocalDateTime createdOn;
    private String description;
    private Boolean enabled = false;
    private Sport sport;
    private UserEntity creator;

    public Offer() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> picturesLinks) {
        this.pictures = picturesLinks;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "created_on",nullable = false)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity userEntity) {
        this.creator = userEntity;
    }

    @Enumerated
    @Column(nullable = false)
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Column(nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name = "product_codition")
    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    @Column(name = "tel_contact",nullable = false)
    public String getTelContact() {
        return telContact;
    }

    public void setTelContact(String telContact) {
        this.telContact = telContact;
    }
}
