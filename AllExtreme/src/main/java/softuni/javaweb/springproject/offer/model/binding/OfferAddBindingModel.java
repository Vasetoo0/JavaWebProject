package softuni.javaweb.springproject.offer.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OfferAddBindingModel {

    private static final String LINK_PATTERN = "(http(s?):)([\\/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)";

    private String title;
    private String model;
    private List<@Pattern(regexp = LINK_PATTERN,message = "One or more links are invalid!")String> pictures;
    private String productCondition;
    private BigDecimal price;
    private String telContact;
    private LocalDateTime createdOn = LocalDateTime.now();
    private String description;
    private Sport sport;
    private String creator;

    public OfferAddBindingModel() {
    }


    @Length(min = 5,message = "Title must me at least 5 chars long!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Length(min = 1,message = "Model must be min 1 chars long!")
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

    @NotNull
    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    @DecimalMin(value = "0", message = "Price must be positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Length(min = 50, message = "Description must be at least 50 chars!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
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

    @NotNull(message = "Enter telephone for contact!")
    public String getTelContact() {
        return telContact;
    }

    public void setTelContact(String telContact) {
        this.telContact = telContact;
    }
}
