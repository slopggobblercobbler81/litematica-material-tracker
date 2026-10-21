package com.github.slopggobblercobbler.litematicamaterialtracker.config;

import com.github.slopggobblercobbler.litematicamaterialtracker.Reference;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.List;

public class TrackerConfig implements IConfigHandler {
    private static final String CONFIG_FILE_NAME = Reference.MOD_ID + ".json";
    private static final TrackerConfig INSTANCE = new TrackerConfig();

    public static TrackerConfig getInstance() {
        return INSTANCE;
    }

    // ==========================================
    // Generic Settings
    // ==========================================
    public static class Generic {
        public static final ConfigBoolean MOD_ENABLED = new ConfigBoolean(
                "modEnabled", true, "Master switch for Litematica Material Tracker."
        );

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                MOD_ENABLED
        );
    }

    // ==========================================
    // HUD Settings
    // ==========================================
    public static class Hud {
        public static final ConfigBoolean ENABLE_HUD = new ConfigBoolean(
                "enableHud", true, "Enable or disable live material list HUD overlay."
        );
        public static final ConfigOptionList HUD_POSITION = new ConfigOptionList(
                "hudPosition", HudPosition.TOP_RIGHT, "Anchor point of the Material Tracker HUD."
        );
        public static final ConfigInteger HUD_X_OFFSET = new ConfigInteger(
                "hudXOffset", 8, 0, 1000, "Horizontal pixel offset from screen anchor."
        );
        public static final ConfigInteger HUD_Y_OFFSET = new ConfigInteger(
                "hudYOffset", 8, 0, 1000, "Vertical pixel offset from screen anchor."
        );
        public static final ConfigDouble HUD_SCALE = new ConfigDouble(
                "hudScale", 1.0, 0.5, 3.0, "Render scale multiplier for HUD."
        );
        public static final ConfigInteger MAX_ITEMS_DISPLAYED = new ConfigInteger(
                "maxItemsDisplayed", 12, 1, 40, "Maximum material lines visible in HUD."
        );
        public static final ConfigBoolean COMPACT_MODE = new ConfigBoolean(
                "compactMode", false, "Use minimalist single-line HUD."
        );
        public static final ConfigOptionList SORT_MODE = new ConfigOptionList(
                "sortMode", SortMode.MISSING_FIRST, "Order materials by missing status, quantity, or name."
        );
        public static final ConfigBoolean SHOW_ITEM_ICONS = new ConfigBoolean(
                "showItemIcons", true, "Render 3D item models next to material names."
        );
        public static final ConfigBoolean SHOW_PROGRESS_BAR = new ConfigBoolean(
                "showProgressBar", true, "Show schematic placement completion percentage bar."
        );
        public static final ConfigDouble BACKGROUND_OPACITY = new ConfigDouble(
                "backgroundOpacity", 0.75, 0.0, 1.0, "Background opacity for HUD container."
        );

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                ENABLE_HUD,
                HUD_POSITION,
                HUD_X_OFFSET,
                HUD_Y_OFFSET,
                HUD_SCALE,
                MAX_ITEMS_DISPLAYED,
                COMPACT_MODE,
                SORT_MODE,
                SHOW_ITEM_ICONS,
                SHOW_PROGRESS_BAR,
                BACKGROUND_OPACITY
        );
    }

    // ==========================================
    // Shulker & Container Auditor Settings
    // ==========================================
    public static class Auditor {
        public static final ConfigBoolean ENABLE_SHULKER_AUDIT = new ConfigBoolean(
                "enableShulkerAudit", true, "Inspect shulker boxes inside inventory and sum nested items."
        );
        public static final ConfigBoolean ENABLE_ENDER_CHEST_AUDIT = new ConfigBoolean(
                "enableEnderChestAudit", true, "Inspect player Ender Chest contents and nested containers."
        );
        public static final ConfigBoolean AUDIT_BUNDLE_CONTENTS = new ConfigBoolean(
                "auditBundleContents", true, "Inspect bundle contents in inventory and ender chest."
        );
        public static final ConfigBoolean RECURSIVE_CONTAINER_SCAN = new ConfigBoolean(
                "recursiveContainerScan", true, "Allow multi-level nested scanning (e.g. bundle in shulker)."
        );
        public static final ConfigInteger AUDIT_INTERVAL_TICKS = new ConfigInteger(
                "auditIntervalTicks", 20, 1, 200, "Background audit rate in game ticks (20 ticks = 1 sec)."
        );

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                ENABLE_SHULKER_AUDIT,
                ENABLE_ENDER_CHEST_AUDIT,
                AUDIT_BUNDLE_CONTENTS,
                RECURSIVE_CONTAINER_SCAN,
                AUDIT_INTERVAL_TICKS
        );
    }

    // ==========================================
    // Missing Block Alarms
    // ==========================================
    public static class Alarms {
        public static final ConfigBoolean ENABLE_MISSING_ALARM = new ConfigBoolean(
                "enableMissingAlarm", true, "Play audio alarm when missing materials are encountered."
        );
        public static final ConfigOptionList ALARM_SOUND = new ConfigOptionList(
                "alarmSound", AlarmSound.PLING, "Sound played for missing item notifications."
        );
        public static final ConfigDouble ALARM_VOLUME = new ConfigDouble(
                "alarmVolume", 0.8, 0.0, 1.0, "Audio alarm volume."
        );
        public static final ConfigDouble ALARM_PITCH = new ConfigDouble(
                "alarmPitch", 1.0, 0.5, 2.0, "Audio alarm pitch."
        );
        public static final ConfigInteger ALARM_COOLDOWN_SECONDS = new ConfigInteger(
                "alarmCooldownSeconds", 3, 1, 60, "Minimum seconds between repeated sound alarms."
        );
        public static final ConfigInteger WARN_LOW_THRESHOLD_PERCENT = new ConfigInteger(
                "warnLowThresholdPercent", 15, 1, 100, "Percentage threshold to warn about low stock."
        );

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                ENABLE_MISSING_ALARM,
                ALARM_SOUND,
                ALARM_VOLUME,
                ALARM_PITCH,
                ALARM_COOLDOWN_SECONDS,
                WARN_LOW_THRESHOLD_PERCENT
        );
    }

    // ==========================================
    // Color Themes & Thresholds
    // ==========================================
    public static class Colors {
        public static final ConfigColor COLOR_SUFFICIENT = new ConfigColor(
                "colorSufficient", "0xFF55FF55", "Color when player has all materials in hand."
        );
        public static final ConfigColor COLOR_IN_SHULKER = new ConfigColor(
                "colorInShulker", "0xFF55FFFF", "Color when items are stored in shulkers / Ender Chest."
        );
        public static final ConfigColor COLOR_LOW_STOCK = new ConfigColor(
                "colorLowStock", "0xFFFFAA00", "Color when items are below low-stock threshold."
        );
        public static final ConfigColor COLOR_MISSING = new ConfigColor(
                "colorMissing", "0xFFFF5555", "Color when required materials are missing completely."
        );
        public static final ConfigColor COLOR_BACKGROUND = new ConfigColor(
                "colorBackground", "0xB010141C", "HUD backdrop background color."
        );
        public static final ConfigColor COLOR_BORDER = new ConfigColor(
                "colorBorder", "0xFF2D3748", "HUD border stroke color."
        );

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                COLOR_SUFFICIENT,
                COLOR_IN_SHULKER,
                COLOR_LOW_STOCK,
                COLOR_MISSING,
                COLOR_BACKGROUND,
                COLOR_BORDER
        );
    }

    @Override
    public void load() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);
        if (configFile.isFile() && configFile.exists()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);
            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();
                ConfigUtils.readConfigBase(root, "Generic", Generic.OPTIONS);
                ConfigUtils.readConfigBase(root, "Hud", Hud.OPTIONS);
                ConfigUtils.readConfigBase(root, "Auditor", Auditor.OPTIONS);
                ConfigUtils.readConfigBase(root, "Alarms", Alarms.OPTIONS);
                ConfigUtils.readConfigBase(root, "Colors", Colors.OPTIONS);
                ConfigUtils.readConfigBase(root, "Hotkeys", Hotkeys.HOTKEY_LIST);
            }
        }
    }

    @Override
    public void save() {
        File dir = FileUtils.getConfigDirectory();
        if (!dir.exists() && !dir.mkdirs()) {
            return;
        }

        JsonObject root = new JsonObject();
        ConfigUtils.writeConfigBase(root, "Generic", Generic.OPTIONS);
        ConfigUtils.writeConfigBase(root, "Hud", Hud.OPTIONS);
        ConfigUtils.writeConfigBase(root, "Auditor", Auditor.OPTIONS);
        ConfigUtils.writeConfigBase(root, "Alarms", Alarms.OPTIONS);
        ConfigUtils.writeConfigBase(root, "Colors", Colors.OPTIONS);
        ConfigUtils.writeConfigBase(root, "Hotkeys", Hotkeys.HOTKEY_LIST);

        JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
    }
}
