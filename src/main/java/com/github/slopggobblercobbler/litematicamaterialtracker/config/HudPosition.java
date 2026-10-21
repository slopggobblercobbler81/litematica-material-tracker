package com.github.slopggobblercobbler.litematicamaterialtracker.config;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;

public enum HudPosition implements IConfigOptionListEntry {
    TOP_LEFT("top_left", "Top Left"),
    TOP_RIGHT("top_right", "Top Right"),
    BOTTOM_LEFT("bottom_left", "Bottom Left"),
    BOTTOM_RIGHT("bottom_right", "Bottom Right"),
    CENTER_LEFT("center_left", "Center Left"),
    CENTER_RIGHT("center_right", "Center Right");

    private final String configString;
    private final String displayName;

    HudPosition(String configString, String displayName) {
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
    public HudPosition fromString(String value) {
        return fromStringStatic(value);
    }

    public static HudPosition fromStringStatic(String value) {
        for (HudPosition pos : values()) {
            if (pos.configString.equalsIgnoreCase(value)) {
                return pos;
            }
        }
        return TOP_LEFT;
    }

    /**
     * Calculates the top-left X/Y coordinate to render the HUD overlay.
     */
    public int[] calculatePosition(int screenWidth, int screenHeight, int hudWidth, int hudHeight, int offsetX, int offsetY) {
        int x;
        int y;

        switch (this) {
            case TOP_RIGHT:
                x = screenWidth - hudWidth - offsetX;
                y = offsetY;
                break;
            case BOTTOM_LEFT:
                x = offsetX;
                y = screenHeight - hudHeight - offsetY;
                break;
            case BOTTOM_RIGHT:
                x = screenWidth - hudWidth - offsetX;
                y = screenHeight - hudHeight - offsetY;
                break;
            case CENTER_LEFT:
                x = offsetX;
                y = (screenHeight - hudHeight) / 2 + offsetY;
                break;
            case CENTER_RIGHT:
                x = screenWidth - hudWidth - offsetX;
                y = (screenHeight - hudHeight) / 2 + offsetY;
                break;
            case TOP_LEFT:
            default:
                x = offsetX;
                y = offsetY;
                break;
        }

        // Clamp to screen boundaries
        x = Math.max(2, Math.min(x, screenWidth - hudWidth - 2));
        y = Math.max(2, Math.min(y, screenHeight - hudHeight - 2));

        return new int[]{x, y};
    }
}
