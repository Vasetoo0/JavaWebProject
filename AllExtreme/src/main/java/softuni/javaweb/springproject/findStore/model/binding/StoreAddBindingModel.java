package softuni.javaweb.springproject.findStore.model.binding;


import org.hibernate.validator.constraints.Length;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StoreAddBindingModel {

    private String name;
    private String city;
    private String picture;
    private String description;
    private String mapsLink;
    private String iFrameLink;
    private Sport sport;

    public StoreAddBindingModel() {
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Length(min = 3,message = "City must be min 3 chars long!")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Length(min = 20,message = "Description must be min 20 chars long!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Pattern(regexp = "https:\\/\\/goo\\.gl\\/maps\\/.+",message = "Enter valid maps share link!")
    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

    @Pattern(regexp = "https:\\/\\/www\\.google\\.com\\/maps\\/embed.+",
            message = "Enter valid iFrame maps link!")
    public String getiFrameLink() {
        return iFrameLink;
    }

    public void setiFrameLink(String iFrameLink) {
        this.iFrameLink = iFrameLink;
    }

    @NotNull
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Length(min = 2,message = "Name must be at least 2 chars long!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
