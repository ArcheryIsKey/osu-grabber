package com.archery.osugrabber.osu;

public class Play {

    public String artist;
    public String title;
    public double pp;
    public String player;
    public double beatmap_id;

    public Play(String player, String artist, String title, double pp) {
        this.artist = artist;
        this.player = player;
        this.title = title;
        this.pp = pp;
    }

    public Play(String player, String artist, String title, int beatmap_id, double pp) {
        this.artist = artist;
        this.player = player;
        this.title = title;
        this.pp = pp;
        this.beatmap_id = beatmap_id;
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

}
