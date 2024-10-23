package com.archery.osugrabber.osu;

public class Play {

    public String artist;
    public String title;
    public String difficulty;
    public String mods;
    public double pp;
    public String player;
    public double beatmap_id;
    public String full_name;

    public Play(String player, String artist, String title, String difficulty, String mods, double pp) {
        this.artist = artist;
        this.player = player;
        this.difficulty = difficulty;
        this.mods = mods;
        this.title = title;
        this.pp = pp;
        full_name = mods != "" ? String.format("%s - %s [%s] +%s", artist, title, difficulty, mods) : String.format("%s - %s [%s]", artist, title, difficulty);
    }

    public Play(String player, String artist, String title, String difficulty, int beatmap_id, String mods, double pp) {
        this.artist = artist;
        this.player = player;
        this.difficulty = difficulty;
        this.mods = mods;
        this.title = title;
        this.pp = pp;
        this.beatmap_id = beatmap_id;
        full_name = mods != null ? String.format("%s - %s [%s] +%s", artist, title, difficulty, mods) : String.format("%s - %s [%s]", artist, title, difficulty);
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getMods() {
        return mods;
    }

    public double getPp() {
        return pp;
    }

    public String getPlayer() {
        return player;
    }

    public double getBeatmap_id() {
        return beatmap_id;
    }

    public String getFull_name() {
        return full_name;
    }
}
