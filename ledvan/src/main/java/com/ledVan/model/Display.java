package com.ledVan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author Santosh Patil
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Display implements Serializable {

    private String displayArea;

    private String displayTimingTo;
    private String displayTimingFrom;

    public String getDisplayTimingTo() {
        return displayTimingTo;
    }

    public void setDisplayTimingTo(String displayTimingTo) {
        this.displayTimingTo = displayTimingTo;
    }

    public String getDisplayTimingFrom() {
        return displayTimingFrom;
    }

    public void setDisplayTimingFrom(String displayTimingFrom) {
        this.displayTimingFrom = displayTimingFrom;
    }

    public Integer getPeopleViewed() {
        return peopleViewed;
    }

    public void setPeopleViewed(Integer peopleViewed) {
        this.peopleViewed = peopleViewed;
    }

    public String getDisplayPictureName() {
        return displayPictureName;
    }

    public void setDisplayPictureName(String displayPictureName) {
        this.displayPictureName = displayPictureName;
    }
    private Integer peopleViewed;
    private String displayPictureName;

    public String getDisplayArea() {
        return displayArea;
    }

    public void setDisplayArea(String displayArea) {
        this.displayArea = displayArea;
    }

    @Override
    public String toString() {
        return "Display{" + "displayArea=" + displayArea + ", displayTimingTo=" + displayTimingTo + ", displayTimingFrom=" + displayTimingFrom + ", peopleViewed=" + peopleViewed + ", displayPictureName=" + displayPictureName + '}';
    }

    
}
