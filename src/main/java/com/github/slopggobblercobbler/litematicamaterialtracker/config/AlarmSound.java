package com.github.slopggobblercobbler.litematicamaterialtracker.config;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum AlarmSound implements IConfigOptionListEntry {
    PLING("pling", "Note Block Pling"),
    BELL("bell", "Bell"),
    CHIME("chime", "Note Block Chime"),
    EXPERIENCE("experience", "Experience Orb"),
    ANVIL_LAND("anvil", "Anvil Land (Urgent)"),
    BASS("bass", "Note Block Bass");

    private final String configString;
    private final String displayName;

    AlarmSound(String configString, String displayName) {
        this.configString = configString;
        this.displayName = displayName;
    }

    public SoundEvent getSoundEvent() {
        switch (this) {
            case BELL:
                return SoundEvents.BLOCK_BELL_USE;
            case CHIME:
                return SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value();
            case EXPERIENCE:
                return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            case ANVIL_LAND:
                return SoundEvents.BLOCK_ANVIL_LAND;
            case BASS:
                return SoundEvents.BLOCK_NOTE_BLOCK_BASS.value();
            case PLING:
            default:
                return SoundEvents.BLOCK_NOTE_BLOCK_PLING.value();
        }
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
    public AlarmSound fromString(String value) {
        return fromStringStatic(value);
    }

    public static AlarmSound fromStringStatic(String value) {
        for (AlarmSound sound : values()) {
            if (sound.configString.equalsIgnoreCase(value)) {
                return sound;
            }
        }
        return PLING;
    }
}
