package com.archery.osugrabber.enums;

public enum GameMode {

    STANDARD(0, "osu!"),
    MANIA(3, "osu!mania"),
    TAIKO(1, "osu!taiko"),
    CATCH(2, "osu!catch");

    private final int id;
    private final String name;

    GameMode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GameMode fromID(int id) {
        for (GameMode m : values())
            if (m.id == id) return m;
        return null;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
