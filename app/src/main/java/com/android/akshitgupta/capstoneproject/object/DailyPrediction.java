package com.android.akshitgupta.capstoneproject.object;

import java.io.Serializable;

/**
 * Created by akshitgupta on 09/10/16.
 */

public class DailyPrediction implements Serializable {
    String title;
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
