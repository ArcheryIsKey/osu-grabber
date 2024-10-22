package com.archery.osugrabber.osu;

import com.archery.osugrabber.enums.GameMode;

public class Beatmap {

    public String artist;
    public String title;
    public GameMode mode;
    public String mapper;
    public String source;
    public int bpm;
    public int beatmap_ID;
    public int beatmapset_ID;
    public String[] tags;
    public String coverImage = String.format("https://assets.ppy.sh/beatmaps/%s/covers/cover.jpg", beatmapset_ID);

    public Beatmap(String artist, String title, GameMode mode, String mapper, String source, int bpm, int beatmapID, int beatmapset_ID, String[] tags, String coverImage) {
        this.artist = artist;
        this.title = title;
        this.mode = mode;
        this.mapper = mapper;
        this.source = source;
        this.bpm = bpm;
        this.beatmap_ID = beatmapID;
        this.beatmapset_ID = beatmapset_ID;
        this.tags = tags;
        this.coverImage = coverImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String[] getTags() {
        return tags;
    }

    public int getBeatmapset_ID() {
        return beatmapset_ID;
    }

    public int getBeatmap_ID() {
        return beatmap_ID;
    }

    public int getBpm() {
        return bpm;
    }

    public String getSource() {
        return source;
    }

    public String getMapper() {
        return mapper;
    }

    public GameMode getMode() {
        return mode;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
