package com.github.slopggobblercobbler.litematicamaterialtracker.config;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;

public enum SortMode implements IConfigOptionListEntry {
    MISSING_FIRST("missing_first", "Missing First"),
    REQUIRED_DESC("required_desc", "Most Required"),
    NAME_ASC("name_asc", "Item Name (A-Z)"),
    READY_FIRST("ready_first", "Ready In Hand First");

    private final String configString;
    private final String displayName;

    SortMode(String configString, String displayName) {
        this.configString = configString;
        this.displayName = displayName;
    }

    @Override
    public String getStringValue() {
        return this.configString;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward) {
        int id = this.ordinal();
        if (forward) {
            id = (id + 1) % values().length;
        } else {
            id = (id - 1 + values().length) % values().length;
        }
        return values()[id];
    }

    @Override
    public SortMode fromString(String value) {
        return fromStringStatic(value);
    }

    public static SortMode fromStringStatic(String value) {
        for (SortMode mode : values()) {
            if (mode.configString.equalsIgnoreCase(value)) {
                return mode;
            }
        }
        return MISSING_FIRST;
    }
}
