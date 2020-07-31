package softuni.javaweb.springproject.story.model.binding;

import com.sun.xml.bind.v2.TODO;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

public class StoryAddBindingModel {

    private String title;
    private String description;
    private LocalDateTime createdOn;
    private String creator;
    private Sport sport;
    private List<String> pictures;

    public StoryAddBindingModel() {
    }

    @Length(min = 10, message = "Title must be at least 10 chars!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Length(min = 200, message = "Story description ust be descriptive!Min 200 chars! :)")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future!")
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    @Length(min = 3, message = "Creator name must be at least 3 chars!")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @NotNull(message = "Sport must not be null!")
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    //TODO: Make validator for pictures!
    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
