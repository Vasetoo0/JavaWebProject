package softuni.javaweb.springproject.video.model.view;

import softuni.javaweb.springproject.enums.Sport;

public class VideoViewModel {

    private String id;
    private String youTubeLink;
    private String description;

    public VideoViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
