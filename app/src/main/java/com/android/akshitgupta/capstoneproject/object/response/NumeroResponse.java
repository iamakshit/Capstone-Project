package com.android.akshitgupta.capstoneproject.object.response;

import java.io.Serializable;

/**
 * Created by akshitgupta on 08/10/16.
 */

public class NumeroResponse implements Serializable {

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
