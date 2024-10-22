package com.archery.osugrabber.osu;

import com.archery.osugrabber.enums.GameMode;

public class Beatmap {

    public String artist;
    public String title;
    public String version;
    public GameMode mode;
    public String creator;
    public String source;
    public double bpm;
    public int beatmap_ID;
    public int beatmapset_ID;
    public String tags;
    public String coverImage;

    public Beatmap(String artist, String title, String version, GameMode mode, String mapper, String source, double bpm, int beatmapID, int beatmapset_ID, String tags) {
        this.artist = artist;
        this.title = title;
        this.version = version;
        this.mode = mode;
        this.creator = mapper;
        this.source = source;
        this.bpm = bpm;
        this.beatmap_ID = beatmapID;
        this.beatmapset_ID = beatmapset_ID;
        this.tags = tags;
        this.coverImage = String.format("https://assets.ppy.sh/beatmaps/%s/covers/cover.jpg", beatmapset_ID);
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getTags() {
        return tags;
    }

    public int getBeatmapset_ID() {
        return beatmapset_ID;
    }

    public int getBeatmap_ID() {
        return beatmap_ID;
    }

    public double getBpm() {
        return bpm;
    }

    public String getSource() {
        return source;
    }

    public String getCreator() {
        return creator;
    }

    public GameMode getMode() {
        return mode;
    }

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
