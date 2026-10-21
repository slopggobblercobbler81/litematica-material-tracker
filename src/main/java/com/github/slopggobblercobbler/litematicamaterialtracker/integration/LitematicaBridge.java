package com.github.slopggobblercobbler.litematicamaterialtracker.integration;

import fi.dy.masa.litematica.data.DataManager;
import fi.dy.masa.litematica.materials.MaterialListBase;
import fi.dy.masa.litematica.materials.MaterialListEntry;
import fi.dy.masa.litematica.schematic.placement.SchematicPlacement;
import fi.dy.masa.litematica.schematic.placement.SchematicPlacementManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LitematicaBridge {
    private static final LitematicaBridge INSTANCE = new LitematicaBridge();

    public static LitematicaBridge getInstance() {
        return INSTANCE;
    }

    private LitematicaBridge() {}

    /**
     * Checks if a schematic is currently loaded and selected in Litematica.
     */
    public boolean hasActivePlacement() {
        try {
            SchematicPlacementManager manager = DataManager.getSchematicPlacementManager();
            if (manager != null) {
                SchematicPlacement placement = manager.getSelectedSchematicPlacement();
                return placement != null && placement.isEnabled();
            }
        } catch (Throwable ignored) {
        }
        return false;
    }

    /**
     * Gets the display name of the active schematic placement.
     */
    public String getActivePlacementName() {
        try {
            SchematicPlacementManager manager = DataManager.getSchematicPlacementManager();
            if (manager != null) {
                SchematicPlacement placement = manager.getSelectedSchematicPlacement();
                if (placement != null) {
                    return placement.getName();
                }
            }
        } catch (Throwable ignored) {
        }
        return "Unknown Schematic";
    }

    /**
     * Retrieves the current material list entries from Litematica.
     */
    public List<TrackedMaterialEntry> getMaterialEntries() {
        try {
            MaterialListBase materialList = DataManager.getMaterialList();
            if (materialList != null) {
                List<MaterialListEntry> litematicaEntries = materialList.getMaterials();
                if (litematicaEntries != null && !litematicaEntries.isEmpty()) {
                    List<TrackedMaterialEntry> result = new ArrayList<>();
                    for (MaterialListEntry entry : litematicaEntries) {
                        ItemStack stack = entry.getStack();
                        if (stack != null && !stack.isEmpty()) {
                            int total = entry.getCountTotal();
                            int missing = entry.getCountMissing();
                            int placed = entry.getCountPlaced();
                            String name = stack.getName().getString();
                            result.add(new TrackedMaterialEntry(stack, name, total, missing, placed));
                        }
                    }
                    return result;
                }
            }
        } catch (Throwable t) {
            // Graceful fallback if Litematica API changes or is not yet initialized
        }
        return Collections.emptyList();
    }
}
