package com.android.akshitgupta.capstoneproject.utils;
import com.android.akshitgupta.capstoneproject.object.GeoPlaceDetails;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by akshitgupta on 08/10/16.
 */

public class GeoUtils {

    public static GeoPlaceDetails getDescriptionDataFromJson(String jsonStr) {
        GeoPlaceDetails geoPlaceDetails = new GeoPlaceDetails();
        try {

            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONObject result = jsonObj.getJSONObject("result");
            JSONObject geometry = result.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            String latitude = location.getString("lat");
            String longitude = location.getString("lng");
            geoPlaceDetails.setLatitude(latitude);
            geoPlaceDetails.setLongitude(longitude);

        } catch (JSONException e) {
        }
        return geoPlaceDetails;
    }
}
