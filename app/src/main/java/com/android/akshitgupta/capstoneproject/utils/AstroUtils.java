package com.android.akshitgupta.capstoneproject.utils;

import com.android.akshitgupta.capstoneproject.enums.NumeroTable;
import com.android.akshitgupta.capstoneproject.object.NumeroPrediction;
import com.android.akshitgupta.capstoneproject.object.UserProfile;
import com.android.akshitgupta.capstoneproject.object.request.AstroRequest;
import com.android.akshitgupta.capstoneproject.object.response.DailyPredictionResponse;
import com.android.akshitgupta.capstoneproject.object.response.NumeroBasicDetailsResponse;
import com.android.akshitgupta.capstoneproject.object.response.NumeroResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshitgupta on 08/10/16.
 */

public class AstroUtils {

    public final static String basicAuth = "Basic " + "NjAwMDU3OjQxMjNkZjk4MjE1NjE3NTVlZjEzNDM4NjJhNTc1MjJh";
    public final static String BASE_URL = "https://api.vedicrishiastro.com/v1/";
    public final static String DAILY_PREDICTION = "daily_nakshatra_prediction";


    public static NumeroBasicDetailsResponse getNumeroBasicDetailsResponse(String jsonString) {
        NumeroBasicDetailsResponse response = new NumeroBasicDetailsResponse();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            String name = jsonObject.getString("name");
            response.setName(name);
            String date = jsonObject.getString("date");
            response.setDate(date);
            String destinyNumber = jsonObject.getString("destiny_number");
            response.setDestinyNumber(destinyNumber);
            String radicalNumber = jsonObject.getString("radical_number");
            response.setRadicalNumber(radicalNumber);
            String evilNum = jsonObject.getString("evil_num");
            response.setEvilNumber(evilNum);
            String favColor = jsonObject.getString("fav_color");
            response.setFavColor(favColor);
            String favDay = jsonObject.getString("fav_day");
            response.setFavDay(favDay);
            String favGod = jsonObject.getString("fav_god");
            response.setFavGod(favGod);
            String favMantra = jsonObject.getString("fav_mantra");
            response.setFavMantra(favMantra);
            String favMetal = jsonObject.getString("fav_metal");
            response.setFavMetal(favMetal);
            String favStone = jsonObject.getString("fav_stone");
            response.setFavStone(favStone);
            String favSubstone = jsonObject.getString("fav_substone");
            response.setFavSubStone(favSubstone);
            String radicalRuler = jsonObject.getString("radical_ruler");
            response.setRadicalRuler(radicalRuler);
            String friendlyNum = jsonObject.getString("friendly_num");
            response.setFriendlyNum(friendlyNum);
            String neutralNum = jsonObject.getString("neutral_num");
            response.setNeutralNum(neutralNum);
            String radicalNum = jsonObject.getString("radical_num");
            response.setRadicalNum(radicalNum);

            return response;
        } catch (JSONException e) {
        }
        return response;

    }


    public static NumeroResponse getNumeroResponse(String jsonString) {
        NumeroResponse response = new NumeroResponse();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String title = jsonObject.getString("title");
            String description = jsonObject.getString("description");
            response.setDescription(description);
            response.setTitle(title);
        } catch (JSONException e) {
        }
        return response;

    }

    public static DailyPredictionResponse getDailyPredictionResponse(String jsonString) {
        DailyPredictionResponse response = new DailyPredictionResponse();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject predictionObject = jsonObject.getJSONObject("prediction");
            String health = predictionObject.getString("health");
            String emotions = predictionObject.getString("emotions");
            String profession = predictionObject.getString("profession");
            String luck = predictionObject.getString("luck");
            String personalLife = predictionObject.getString("personal_life");
            String travel = predictionObject.getString("travel");

            response.setPersonalLife(personalLife);
            response.setTravel(travel);
            response.setHealth(health);
            response.setEmotions(emotions);
            response.setProfession(profession);
            response.setLuck(luck);


        } catch (JSONException e) {
        }
        return response;

    }

    public static String prepareAstroRequestString(AstroRequest request) {

        StringBuilder requestString = new StringBuilder();
        if (StringUtils.isNotEmpty(request.getName())) {
            requestString.append("name=").append(request.getName()).append("&");
        }

        if (request.getDay() != null) {
            requestString.append("day=").append(request.getDay()).append("&");
        }

        if (request.getMonth() != null) {
            requestString.append("month=").append(request.getMonth()).append("&");

        }

        if (request.getYear() != null) {
            requestString.append("year=").append(request.getYear()).append("&");
        }

        if (request.getHour() != null) {
            requestString.append("hour=").append(request.getHour()).append("&");
        }

        if (request.getMin() != null) {
            requestString.append("min=").append(request.getMin()).append("&");
        }
        if (request.getLat() != null) {
            requestString.append("lat=").append(request.getLat()).append("&");
        }
        if (request.getLon() != null) {
            requestString.append("lon=").append(request.getLon()).append("&");
        }
        if (request.getTzone() != null) {
            requestString.append("tzone=").append(request.getTzone()).append("&");
        }

        if (requestString.length() > 0) {
            requestString.setLength(requestString.length() - 1);
        }
        return requestString.toString();
    }


    public static AstroRequest getAstroRequestByUserProfile(UserProfile.User user, String numerologyCode) {
        String[] date = user.getDobDate().split("-");
        String[] hour = user.getDobTIme().split(":");

        AstroRequest request = new AstroRequest();
        request.setAstroURL(numerologyCode);
        request.setName(user.getUserName());
        request.setDay(date[0]);
        request.setMonth(date[1]);
        request.setYear(date[2]);
        request.setHour(hour[0]);
        request.setMin(hour[1]);
        request.setLat(user.getCoordLat());
        request.setLon(user.getCoordLong());
        request.setTzone(5.5);
        return request;
    }

    public static List<NumeroPrediction> prepareNumeroPredictionFromBasicDetailsResponse(NumeroBasicDetailsResponse response) {
        List<NumeroPrediction> numeroPredictionList = new ArrayList<>();

        numeroPredictionList.add(new NumeroPrediction(NumeroTable.NAME.getTitle(), response.getName()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.DATE.getTitle(), response.getDate()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.DESTINY_NUMBER.getTitle(), response.getDestinyNumber()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.RADICAL_NUMBER.getTitle(), response.getRadicalNumber()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.NAME_NUMBER.getTitle(), response.getNameNumber()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.EVIL_NUMBER.getTitle(), response.getEvilNumber()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_COLOR.getTitle(), response.getFavColor()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_DAY.getTitle(), response.getFavDay()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_GOD.getTitle(), response.getFavGod()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_MANTRA.getTitle(), response.getFavMantra()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_METAL.getTitle(), response.getFavMetal()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_STONE.getTitle(), response.getFavStone()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FAV_SUBSTONE.getTitle(), response.getFavSubStone()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.FRIENDLY_NUMBER.getTitle(), response.getFriendlyNum()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.NEUTRAL_NUMER.getTitle(), response.getNeutralNum()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.RADICAL_NUMBER.getTitle(), response.getRadicalNum()));
        numeroPredictionList.add(new NumeroPrediction(NumeroTable.RADICAL_RULER.getTitle(), response.getRadicalRuler()));

        return numeroPredictionList;
    }
}
