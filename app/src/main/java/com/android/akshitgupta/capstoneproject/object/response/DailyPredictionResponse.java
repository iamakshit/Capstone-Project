package com.android.akshitgupta.capstoneproject.object.response;

import java.io.Serializable;

/**
 * Created by akshitgupta on 08/10/16.
 */

public class DailyPredictionResponse implements Serializable {

    String health;
    String emotions;
    String profession;
    String luck;
    String personalLife;
    String travel;

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getEmotions() {
        return emotions;
    }

    public void setEmotions(String emotions) {
        this.emotions = emotions;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLuck() {
        return luck;
    }

    public void setLuck(String luck) {
        this.luck = luck;
    }

    public String getPersonalLife() {
        return personalLife;
    }

    public void setPersonalLife(String personalLife) {
        this.personalLife = personalLife;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }


}
