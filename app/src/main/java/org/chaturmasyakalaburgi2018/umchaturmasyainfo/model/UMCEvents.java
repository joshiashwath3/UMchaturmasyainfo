package org.chaturmasyakalaburgi2018.umchaturmasyainfo.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by 19SIMBLS LLP on 22,July,2018
 *
 * @Author Ashwath Joshi
 */
@IgnoreExtraProperties
public class UMCEvents {

    private int eventId;

    private String eventDate;

    private String eventTitle;

    private String eventSubTiltle;

    private String eventDiscription;

    public UMCEvents() {
    }

    public UMCEvents(int eventId, String eventTitle, String eventDiscription, String eventSubTiltle, String eventDate) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventDiscription = eventDiscription;
        this.eventSubTiltle = eventSubTiltle;
        this.eventDate = eventDate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventSubTiltle() {
        return eventSubTiltle;
    }

    public void setEventSubTiltle(String eventSubTiltle) {
        this.eventSubTiltle = eventSubTiltle;
    }

    public String getEventDiscription() {
        return eventDiscription;
    }

    public void setEventDiscription(String eventDiscription) {
        this.eventDiscription = eventDiscription;
    }
}
