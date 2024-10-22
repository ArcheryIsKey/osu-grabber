package com.archery.osugrabber.osu;

public class User {

    public int user_id;
    public String username;
    public String join_date;
    public int rank;
    public double pp;
    public String profile_image = "https://a.ppy.sh/" + user_id;

    public User(int user_id, String username, String join_date, int rank, double pp, String profile_image) {
        this.user_id = user_id;
        this.username = username;
        this.join_date = join_date;
        this.rank = rank;
        this.pp = pp;
        this.profile_image = profile_image;
    }

    public int getUserID() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getJoinDate() {
        return join_date;
    }

    public int getRank() {
        return rank;
    }

    public double getPP() {
        return pp;
    }

    public String getProfileImage() {
        return profile_image;
    }

}
