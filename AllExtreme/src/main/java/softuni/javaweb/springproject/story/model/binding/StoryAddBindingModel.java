package softuni.javaweb.springproject.story.model.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StoryAddBindingModel {

    private String title;
    private List<String> picturesLinks = new ArrayList<>();
    private String description;
    private LocalDateTime createdOn;
    private String creator;
    private Sport sport;

    public StoryAddBindingModel() {
    }

    @Length(min = 10, message = "Title must be at least 10 chars!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public List<@Pattern(regexp = "^https:\\/\\/.+\\.jpg$|^https:\\/\\/.+\\.png$",
            message = "Invalid link!") String> getPicturesLinks() {
        return picturesLinks;
    }

    public void setPicturesLinks(List<String> picturesLinks) {
        this.picturesLinks = picturesLinks;
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

    @NotNull
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
