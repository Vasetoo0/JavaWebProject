package softuni.javaweb.springproject.video.model.binding;

import org.hibernate.validator.constraints.Length;
import softuni.javaweb.springproject.enums.Sport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class VideoAddBindingModel {

    private String title;
    private String youTubeLink;
    private String description;
    private Sport sport;

    public VideoAddBindingModel() {
    }


    @Pattern(regexp = "((http(s)?:\\/\\/)?)(www\\.)?((youtube\\.com\\/)|(youtu.be\\/))[\\S]+",
    message = "Enter valid YouTube link!")
    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    @Length(min = 10, message = "Video must have description ata least 10 chars!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Sport must be selected!")
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    @Length(min = 5, message = "Title must be at least 5 chars!")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
