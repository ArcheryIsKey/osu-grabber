package com.archery.osugrabber.enums;

public enum Genre {

    ANY(0),
    UNSPECIFIED(1),
    VIDEO_GAME(2),
    ANIME(3),
    ROCK(4),
    POP(5),
    OTHER(6),
    NOVELTY(7),
    HIP_HOP(9),
    ELECTRONIC(10),
    METAL(11),
    CLASSICAL(12),
    FOLK(13),
    JAZZ(14);

    private final int id;

    Genre(int id) {
        this.id = id;
    }

    public static Genre fromID(int id) {
        for (Genre genre : values()) {
            if(genre.id == id) return genre;
        }

        return null;
    }
}
