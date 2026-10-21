package com.github.slopggobblercobbler.litematicamaterialtracker.config;

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
}
