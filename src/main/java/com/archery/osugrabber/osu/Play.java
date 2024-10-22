package com.archery.osugrabber.osu;

public class Play {

    public String artist;
    public String title;
    public String difficulty;
    public double pp;
    public String player;
    public double beatmap_id;
    public String full_name;

    public Play(String player, String artist, String title, String difficulty, double pp) {
        this.artist = artist;
        this.player = player;
        this.difficulty = difficulty;
        this.title = title;
        this.pp = pp;
        full_name = String.format("%s - %s [%s]", artist, title, difficulty);
    }

    public Play(String player, String artist, String title, String difficulty, int beatmap_id, double pp) {
        this.artist = artist;
        this.player = player;
        this.difficulty = difficulty;
        this.title = title;
        this.pp = pp;
        this.beatmap_id = beatmap_id;
        full_name = String.format("%s - %s [%s]", artist, title, difficulty);
    }

    public String getTitle() {
        return title;
    }

    public double getPp() {
        return pp;
    }

    public String getPlayer() {
        return player;
    }

    public String getFull_name() {
        return full_name;
    }


}
