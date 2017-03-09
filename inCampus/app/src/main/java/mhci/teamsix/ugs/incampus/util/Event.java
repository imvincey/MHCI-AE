package mhci.teamsix.ugs.incampus.util;

import java.io.Serializable;

/**
 * Created by Vincey on 6/3/2017.
 */

public class Event implements Serializable{
    private String eventId;
    private String eventName;
    private String date;
    private String location;
    private String[] imgURL;
    private String description;

    public Event() {

    }

    public Event(String eventId, String eventName, String date, String location, String[] imgURL, String description) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.imgURL = imgURL;
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getImgURL() {
        return imgURL;
    }

    public void setImgURL(String[] imgURL) {
        this.imgURL = imgURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
