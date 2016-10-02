package com.android.akshitgupta.capstoneproject.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class UserProfile {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<User> ITEMS = new ArrayList<User>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, User> ITEM_MAP = new HashMap<Integer, User>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(User item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static User createDummyItem(int position) {
        return new User(position, "Akshit", "M", "", "23:30", "Iamge", "Delhi", "56.7N", "23.3E");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class User {
        private Integer id;
        private String userName;
        private String userGender;
        private String dobDate;
        private String dobTIme;
        private String userImage;
        private String cityName;
        private String coordLat;
        private String coordLong;

        public User(Integer id, String userName, String userGender, String dobDate, String dobTIme, String userImage, String cityName, String coordLat, String coordLong) {
            this.id = id;
            this.userName = userName;
            this.userGender = userGender;
            this.dobDate = dobDate;
            this.dobTIme = dobTIme;
            this.userImage = userImage;
            this.cityName = cityName;
            this.coordLat = coordLat;
            this.coordLong = coordLong;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public void setDobDate(String dobDate) {
            this.dobDate = dobDate;
        }

        public void setDobTIme(String dobTIme) {
            this.dobTIme = dobTIme;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public void setCoordLat(String coordLat) {
            this.coordLat = coordLat;
        }

        public void setCoordLong(String coordLong) {
            this.coordLong = coordLong;
        }

        public Integer getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserGender() {
            return userGender;
        }

        public String getDobDate() {
            return dobDate;
        }

        public String getDobTIme() {
            return dobTIme;
        }

        public String getUserImage() {
            return userImage;
        }

        public String getCityName() {
            return cityName;
        }

        public String getCoordLat() {
            return coordLat;
        }

        public String getCoordLong() {
            return coordLong;
        }
    }
}
