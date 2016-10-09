package com.android.akshitgupta.capstoneproject.enums;


/**
 * Created by akshitgupta on 02/10/16.
 */

public enum Gender {
    MALE("male"), FEMALE("female");
    String code;

    private Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
