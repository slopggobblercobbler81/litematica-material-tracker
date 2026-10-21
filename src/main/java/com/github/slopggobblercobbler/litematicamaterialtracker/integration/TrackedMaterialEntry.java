package com.github.slopggobblercobbler.litematicamaterialtracker.integration;

import net.minecraft.item.ItemStack;

public class TrackedMaterialEntry {
    private final ItemStack stack;
    private final String name;
    private final int totalRequired;
    private final int totalMissing;
    private final int totalPlaced;

    public TrackedMaterialEntry(ItemStack stack, String name, int totalRequired, int totalMissing, int totalPlaced) {
        this.stack = stack;
        this.name = name;
        this.totalRequired = totalRequired;
        this.totalMissing = totalMissing;
        this.totalPlaced = totalPlaced;
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalRequired() {
        return this.totalRequired;
    }

    public int getTotalMissing() {
        return this.totalMissing;
    }

    public int getTotalPlaced() {
        return this.totalPlaced;
    }
}
