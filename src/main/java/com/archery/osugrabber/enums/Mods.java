package com.archery.osugrabber.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Mods {

    NONE(0,""),
    NOFAIL(1, "NF"),
    EASY(2, "EZ"),
    TOUCHDEVICE(4, "TD"),
    HIDDEN(8, "HD"),
    HARDROCK(16, "HR"),
    SUDDENDEATH(32, "SD"),
    DOUBLETIME(64, "DT"),
    RELAX(128, "RX"),
    HALFTIME(256, "HT"),
    NIGHTCORE(512, "NC"),
    FLASHLIGHT(1024, "FL"),
    AUTOPLAY(2048, "AP"),
    SPUNOUT(4096, "SO"),
    AUTOPILOT(8192, "AUTO"),
    PERFECT(16384, "PF"),
    KEY4(32768, "4K"),
    KEY5(65536, "5K"),
    KEY6(131072, "6K"),
    KEY7(262144, "7K"),
    KEY8(524288, "8K"),
    FADEIN(1048576, "FADEIN"),
    RANDOM(2097152, "RANDOM"),
    CINEMA(4194304, "CINEMA"),
    TARGET(8388608, "TARGET"),
    KEY9(16777216, "9K"),
    KEYCOOP(33554432, "KEYCOOP"),
    KEY1(67108864, "1K"),
    KEY3(134217728, "3K"),
    KEY2(268435456, "2K"),
    SCOREV2(536870912, "V2"),
    MIRROR(1073741824, "MIRROR");

    public String getShortened(Mods mods) {
        return mods.shortened;
    }

    private final String shortened;

    private final int id;

    Mods(int id, String shortened) {
        this.shortened = shortened;
        this.id = id;
    }

    public static Mods fromID(int id) {
        for (Mods mod : values()) {
            if(mod.id == id) {
                return mod;
            }
        }
        return null;
    }

    public static String toShortened(Mods mods) {
        for (Mods mod : values()) {
            if (mods == mod) {
                return mod.shortened;
            }
        }
        return null;
    }

    public static List<Mods> toShortened(int id) {
        if(id != 0) {
            List<Mods> values = Arrays.stream(values())
                    .sorted(Comparator.comparingInt(Mods::getId))
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            List<Mods> mods = new ArrayList<>();
            while (id > 0) for (Mods mod : values)
                if (mod.id <= id) {
                    mods.add(mod);
                    id -= mod.id;
                }
                return mods;
        }
        return null;
    }

    public static String fromListToMods(List<Mods> mods) {
        StringBuilder string = new StringBuilder();
        if(mods != null)
            for (Mods mod : mods) {
                if(mod != null)
                    string.append(toShortened(mod));
        }
        return string.toString().strip();
    }

    public static Mods fromShortened(String shortened) {
        for(Mods mod : values()) {
            if(mod.shortened == shortened) return mod;
        }
        return null;
    }

    public int getId() {
        return id;
    }

}