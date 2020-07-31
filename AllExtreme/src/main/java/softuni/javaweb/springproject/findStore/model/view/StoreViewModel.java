package softuni.javaweb.springproject.findStore.model.view;

public class StoreViewModel {

    private String picture;
    private String name;
    private String city;
    private String description;
    private String mapsLink;
    private String iFrameLink;

    public StoreViewModel() {
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
