package com.github.slopggobblercobbler.litematicamaterialtracker.auditor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InventoryAuditResult {
    public static final InventoryAuditResult EMPTY = new InventoryAuditResult(
            Collections.emptyMap(), 0, 0, false, 0
    );

    private final Map<Item, AuditedItemCounts> itemCounts;
    private final int shulkersScanned;
    private final int bundlesScanned;
    private final boolean enderChestScanned;
    private final long timestamp;

    public InventoryAuditResult(
            Map<Item, AuditedItemCounts> itemCounts,
            int shulkersScanned,
            int bundlesScanned,
            boolean enderChestScanned,
            long timestamp) {
        this.itemCounts = Collections.unmodifiableMap(new HashMap<>(itemCounts));
        this.shulkersScanned = shulkersScanned;
        this.bundlesScanned = bundlesScanned;
        this.enderChestScanned = enderChestScanned;
        this.timestamp = timestamp;
    }

    public AuditedItemCounts getCounts(Item item) {
        return this.itemCounts.getOrDefault(item, new AuditedItemCounts());
    }

    public AuditedItemCounts getCounts(ItemStack stack) {
        return getCounts(stack.getItem());
    }

    public Map<Item, AuditedItemCounts> getAllCounts() {
        return this.itemCounts;
    }

    public int getShulkersScanned() {
        return this.shulkersScanned;
    }

    public int getBundlesScanned() {
        return this.bundlesScanned;
    }

    public boolean isEnderChestScanned() {
        return this.enderChestScanned;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
