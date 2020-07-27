package softuni.javaweb.springproject.destination.model.view;

import java.util.List;

public class DestinationViewModel {

    private String title;
    private String mapsLink;
    private String iFrameLink;
    private List<String> pictures;
    private String description;

    public DestinationViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String mapsLink) {
        this.mapsLink = mapsLink;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
