package softuni.javaweb.springproject.event.model.view;

import java.time.LocalDateTime;
import java.util.List;

public class AllEventViewModel {

    private String id;
    private String title;
    private LocalDateTime eventDate;
    private List<String> pictures;

    public AllEventViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
