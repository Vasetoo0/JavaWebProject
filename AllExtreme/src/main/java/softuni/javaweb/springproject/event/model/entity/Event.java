package softuni.javaweb.springproject.event.model.entity;

import softuni.javaweb.springproject.base.BaseEntity;
import softuni.javaweb.springproject.enums.Sport;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {

    private String title;
    private String mapsLink;
    private LocalDateTime eventDate;
    private Double ticketPrice;
    private List<String> pictures;
    private String type;
    private String description;
    private Sport sport;

    public Event() {
    }

    @Column(nullable = false,unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getMapsLink() {
        return mapsLink;
    }

    public void setMapsLink(String destination) {
        this.mapsLink = destination;
    }

    @Column(name = "event_date",nullable = false)
    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    @Column(name = "ticket_price",nullable = false)
    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> picturesLinks) {
        this.pictures = picturesLinks;
    }

    @Column(nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated
    @Column(nullable = false)
    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
