package com.android.akshitgupta.capstoneproject.enums;

import java.util.HashMap;

/**
 * Created by akshitgupta on 09/10/16.
 */

public enum NumeroTable {
    NAME("name", "Name"),
    DATE("date", "Date Of Birth"),
    DESTINY_NUMBER("destiny_number", "Destiny Number"),
    RADICAL_NUMBER("radical_number", "Radical Number"),
    NAME_NUMBER("name_number", "Name Number"),
    EVIL_NUMBER("evil_num", "Evil Number"),
    FAV_COLOR("fav_color", "Favourable Color"),
    FAV_DAY("fav_day", "Favourable Day"),
    FAV_GOD("fav_god", "Favourable God"),
    FAV_MANTRA("fav_mantra", "Favourable Mantra"),
    FAV_METAL("fav_metal", "Favourable Metal"),
    FAV_STONE("fav_stone", "Favourable Stone"),
    FAV_SUBSTONE("fav_substone", "Favourable Substone"),
    FRIENDLY_NUMBER("friendly_num", "Friendly Number"),
    NEUTRAL_NUMER("neutral_num", "Neutral Number"),
    RADICAL_RULER("radical_ruler", "Radical Ruler");

    static HashMap<String, NumeroTable> map = new HashMap<String, NumeroTable>();

    static {
        for (NumeroTable numeroTable : NumeroTable.values()) {
            map.put(numeroTable.getCode(), numeroTable);
        }
    }

    String code;
    String title;


    NumeroTable(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public NumeroTable getNumeroTableByCode(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
