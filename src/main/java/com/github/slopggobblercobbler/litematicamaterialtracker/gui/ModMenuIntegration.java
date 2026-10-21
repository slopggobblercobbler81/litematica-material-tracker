package com.github.slopggobblercobbler.litematicamaterialtracker.gui;

import com.github.slopggobblercobbler.litematicamaterialtracker.config.TrackerConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parentScreen)
                    .setTitle(Text.translatable("title.litematica_material_tracker.config"));

            builder.setSavingRunnable(() -> {
                TrackerConfig.getInstance().save();
            });

            // Generic Category
            ConfigCategory genericCategory = builder.getOrCreateCategory(Text.translatable("category.litematica_material_tracker.generic"));
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            genericCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.mod_enabled"),
                            TrackerConfig.Generic.MOD_ENABLED.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Generic.MOD_ENABLED.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.mod_enabled.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Generic.MOD_ENABLED.setBooleanValue(newValue))
                    .build());

            // HUD Category
            ConfigCategory hudCategory = builder.getOrCreateCategory(Text.translatable("category.litematica_material_tracker.hud"));

            hudCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.enable_hud"),
                            TrackerConfig.Hud.ENABLE_HUD.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Hud.ENABLE_HUD.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.enable_hud.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.ENABLE_HUD.setBooleanValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startIntField(
                            Text.translatable("option.litematica_material_tracker.hud_x_offset"),
                            TrackerConfig.Hud.HUD_X_OFFSET.getIntegerValue())
                    .setDefaultValue(TrackerConfig.Hud.HUD_X_OFFSET.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.hud_x_offset.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.HUD_X_OFFSET.setIntegerValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startIntField(
                            Text.translatable("option.litematica_material_tracker.hud_y_offset"),
                            TrackerConfig.Hud.HUD_Y_OFFSET.getIntegerValue())
                    .setDefaultValue(TrackerConfig.Hud.HUD_Y_OFFSET.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.hud_y_offset.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.HUD_Y_OFFSET.setIntegerValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startDoubleSlider(
                            Text.translatable("option.litematica_material_tracker.hud_scale"),
                            TrackerConfig.Hud.HUD_SCALE.getDoubleValue(), 0.5, 3.0)
                    .setDefaultValue(TrackerConfig.Hud.HUD_SCALE.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.hud_scale.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.HUD_SCALE.setDoubleValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.litematica_material_tracker.max_items_displayed"),
                            TrackerConfig.Hud.MAX_ITEMS_DISPLAYED.getIntegerValue(), 1, 40)
                    .setDefaultValue(TrackerConfig.Hud.MAX_ITEMS_DISPLAYED.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.max_items_displayed.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.MAX_ITEMS_DISPLAYED.setIntegerValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.compact_mode"),
                            TrackerConfig.Hud.COMPACT_MODE.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Hud.COMPACT_MODE.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.compact_mode.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.COMPACT_MODE.setBooleanValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.show_item_icons"),
                            TrackerConfig.Hud.SHOW_ITEM_ICONS.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Hud.SHOW_ITEM_ICONS.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.show_item_icons.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.SHOW_ITEM_ICONS.setBooleanValue(newValue))
                    .build());

            hudCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.show_progress_bar"),
                            TrackerConfig.Hud.SHOW_PROGRESS_BAR.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Hud.SHOW_PROGRESS_BAR.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.show_progress_bar.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Hud.SHOW_PROGRESS_BAR.setBooleanValue(newValue))
                    .build());

            // Auditor Category
            ConfigCategory auditorCategory = builder.getOrCreateCategory(Text.translatable("category.litematica_material_tracker.auditor"));

            auditorCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.enable_shulker_audit"),
                            TrackerConfig.Auditor.ENABLE_SHULKER_AUDIT.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Auditor.ENABLE_SHULKER_AUDIT.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.enable_shulker_audit.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Auditor.ENABLE_SHULKER_AUDIT.setBooleanValue(newValue))
                    .build());

            auditorCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.enable_ender_chest_audit"),
                            TrackerConfig.Auditor.ENABLE_ENDER_CHEST_AUDIT.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Auditor.ENABLE_ENDER_CHEST_AUDIT.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.enable_ender_chest_audit.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Auditor.ENABLE_ENDER_CHEST_AUDIT.setBooleanValue(newValue))
                    .build());

            auditorCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.audit_bundle_contents"),
                            TrackerConfig.Auditor.AUDIT_BUNDLE_CONTENTS.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Auditor.AUDIT_BUNDLE_CONTENTS.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.audit_bundle_contents.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Auditor.AUDIT_BUNDLE_CONTENTS.setBooleanValue(newValue))
                    .build());

            auditorCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.recursive_container_scan"),
                            TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.recursive_container_scan.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.setBooleanValue(newValue))
                    .build());

            auditorCategory.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.litematica_material_tracker.audit_interval_ticks"),
                            TrackerConfig.Auditor.AUDIT_INTERVAL_TICKS.getIntegerValue(), 1, 200)
                    .setDefaultValue(TrackerConfig.Auditor.AUDIT_INTERVAL_TICKS.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.audit_interval_ticks.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Auditor.AUDIT_INTERVAL_TICKS.setIntegerValue(newValue))
                    .build());

            // Alarms Category
            ConfigCategory alarmsCategory = builder.getOrCreateCategory(Text.translatable("category.litematica_material_tracker.alarms"));

            alarmsCategory.addEntry(entryBuilder.startBooleanToggle(
                            Text.translatable("option.litematica_material_tracker.enable_missing_alarm"),
                            TrackerConfig.Alarms.ENABLE_MISSING_ALARM.getBooleanValue())
                    .setDefaultValue(TrackerConfig.Alarms.ENABLE_MISSING_ALARM.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.enable_missing_alarm.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Alarms.ENABLE_MISSING_ALARM.setBooleanValue(newValue))
                    .build());

            alarmsCategory.addEntry(entryBuilder.startDoubleSlider(
                            Text.translatable("option.litematica_material_tracker.alarm_volume"),
                            TrackerConfig.Alarms.ALARM_VOLUME.getDoubleValue(), 0.0, 1.0)
                    .setDefaultValue(TrackerConfig.Alarms.ALARM_VOLUME.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.alarm_volume.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Alarms.ALARM_VOLUME.setDoubleValue(newValue))
                    .build());

            alarmsCategory.addEntry(entryBuilder.startDoubleSlider(
                            Text.translatable("option.litematica_material_tracker.alarm_pitch"),
                            TrackerConfig.Alarms.ALARM_PITCH.getDoubleValue(), 0.5, 2.0)
                    .setDefaultValue(TrackerConfig.Alarms.ALARM_PITCH.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.alarm_pitch.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Alarms.ALARM_PITCH.setDoubleValue(newValue))
                    .build());

            alarmsCategory.addEntry(entryBuilder.startIntField(
                            Text.translatable("option.litematica_material_tracker.alarm_cooldown_seconds"),
                            TrackerConfig.Alarms.ALARM_COOLDOWN_SECONDS.getIntegerValue())
                    .setDefaultValue(TrackerConfig.Alarms.ALARM_COOLDOWN_SECONDS.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.alarm_cooldown_seconds.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Alarms.ALARM_COOLDOWN_SECONDS.setIntegerValue(newValue))
                    .build());

            alarmsCategory.addEntry(entryBuilder.startIntSlider(
                            Text.translatable("option.litematica_material_tracker.warn_low_threshold_percent"),
                            TrackerConfig.Alarms.WARN_LOW_THRESHOLD_PERCENT.getIntegerValue(), 1, 100)
                    .setDefaultValue(TrackerConfig.Alarms.WARN_LOW_THRESHOLD_PERCENT.getDefaultValue())
                    .setTooltip(Text.translatable("option.litematica_material_tracker.warn_low_threshold_percent.tooltip"))
                    .setSaveConsumer(newValue -> TrackerConfig.Alarms.WARN_LOW_THRESHOLD_PERCENT.setIntegerValue(newValue))
                    .build());

            return builder.build();
        };
    }
}
