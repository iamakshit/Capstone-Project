package com.android.akshitgupta.capstoneproject.utils;

/**
 * Created by akshitgupta on 08/10/16.
 */

public enum Numerology {

    GENERAL_STATS("numero_table"),
    NUMBER_DETAILS("numero_report"),
    FAV_TIME("numero_fav_time"),
    FAV_MANTRA("numero_fav_mantra"),
    FAV_VASTU("numero_place_vastu"),
    FAV_LORD("numero_fav_lord");



    String code;

    Numerology(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
