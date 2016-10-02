package com.android.akshitgupta.capstoneproject.object;

/**
 * Created by akshitgupta on 29/09/16.
 */

public class GeoDetails {

    String placeId;
    String description;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() { //Should not be changed. It is based on this that list is shown
        return  description;
    }
}
