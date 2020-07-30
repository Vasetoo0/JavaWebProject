package softuni.javaweb.springproject.offer.model.view;


import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

public class AllOfferViewModel {

    private String id;
    private String title;
    private List<String> pictures;
    private BigDecimal price;
    private Sport sport;

    public AllOfferViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
