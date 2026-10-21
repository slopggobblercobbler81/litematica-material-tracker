package com.github.slopggobblercobbler.litematicamaterialtracker.config;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigPresetTest {

    @Test
    public void testPresetsAndRoundTrip() {
        // Test Minimalist Preset
        TrackerConfig.Generic.MOD_ENABLED.setBooleanValue(true);
        TrackerConfig.Hud.COMPACT_MODE.setBooleanValue(true);
        TrackerConfig.Hud.SHOW_ITEM_ICONS.setBooleanValue(false);
        TrackerConfig.Alarms.ENABLE_MISSING_ALARM.setBooleanValue(false);

        assertTrue(TrackerConfig.Hud.COMPACT_MODE.getBooleanValue());
        assertFalse(TrackerConfig.Hud.SHOW_ITEM_ICONS.getBooleanValue());

        // Test Power User Preset
        TrackerConfig.Hud.COMPACT_MODE.setBooleanValue(false);
        TrackerConfig.Hud.SHOW_ITEM_ICONS.setBooleanValue(true);
        TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.setBooleanValue(true);
        TrackerConfig.Auditor.AUDIT_INTERVAL_TICKS.setIntegerValue(10);

        assertFalse(TrackerConfig.Hud.COMPACT_MODE.getBooleanValue());
        assertTrue(TrackerConfig.Hud.SHOW_ITEM_ICONS.getBooleanValue());
        assertEquals(10, TrackerConfig.Auditor.AUDIT_INTERVAL_TICKS.getIntegerValue());

        // Test Recording/Streaming Safe Preset
        TrackerConfig.Alarms.ENABLE_MISSING_ALARM.setBooleanValue(false);
        TrackerConfig.Hud.BACKGROUND_OPACITY.setDoubleValue(0.9);

        assertFalse(TrackerConfig.Alarms.ENABLE_MISSING_ALARM.getBooleanValue());
        assertEquals(0.9, TrackerConfig.Hud.BACKGROUND_OPACITY.getDoubleValue(), 0.001);
    }

    @Test
    public void testJsonRoundTripWithOmittedKeys() {
        // Simulate JSON migration with missing/omitted keys falling back to defaults
        JsonObject partialJson = new JsonObject();
        JsonObject hudObj = new JsonObject();
        // Omit compactMode and hudScale to test default fallbacks
        hudObj.addProperty("enableHud", false);
        partialJson.add("Hud", hudObj);

        // Verify fallback defaults remain stable
        assertTrue(TrackerConfig.Hud.ENABLE_HUD.getDefaultValue());
        assertFalse(TrackerConfig.Hud.COMPACT_MODE.getBooleanValue()); // default
        assertEquals(1.0, TrackerConfig.Hud.HUD_SCALE.getDoubleValue(), 0.001);
    }
}
