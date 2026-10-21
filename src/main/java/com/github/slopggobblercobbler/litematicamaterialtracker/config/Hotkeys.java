package com.github.slopggobblercobbler.litematicamaterialtracker.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

import java.util.List;

public class Hotkeys {
    public static final ConfigHotkey OPEN_CONFIG_GUI = new ConfigHotkey(
            "openConfigGui",
            "M,T",
            "Hotkey to open the Litematica Material Tracker configuration menu."
    );

    public static final ConfigHotkey TOGGLE_HUD = new ConfigHotkey(
            "toggleHud",
            "H",
            KeybindSettings.INGAME_DEFAULT,
            "Hotkey to quickly toggle the Material Tracker HUD overlay."
    );

    public static final ConfigHotkey FORCE_AUDIT = new ConfigHotkey(
            "forceAudit",
            "F7",
            KeybindSettings.INGAME_DEFAULT,
            "Hotkey to force an immediate deep scan of player inventory, Ender Chest, and nested shulkers."
    );

    public static final ConfigHotkey MUTE_ALARMS = new ConfigHotkey(
            "muteAlarms",
            "M,K",
            KeybindSettings.INGAME_DEFAULT,
            "Hotkey to mute or unmute missing material audio alarms."
    );

    public static final List<ConfigHotkey> HOTKEY_LIST = ImmutableList.of(
            OPEN_CONFIG_GUI,
            TOGGLE_HUD,
            FORCE_AUDIT,
            MUTE_ALARMS
    );
}
