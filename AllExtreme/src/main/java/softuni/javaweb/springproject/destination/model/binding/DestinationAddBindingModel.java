package softuni.javaweb.springproject.destination.model.binding;

import org.hibernate.validator.constraints.Length;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class DestinationAddBindingModel {
    private String title;
    private String mapsLink;
    private String iFrameLink;
    private List<String> pictures;
    private String description;
    private Sport sport;

    public DestinationAddBindingModel() {
    }

    @Length(min = 5, message = "Destination title must be at least 5 chars!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    @Length(min = 100,message = "Description must be at least 100 chars!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Please select sport!")
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
