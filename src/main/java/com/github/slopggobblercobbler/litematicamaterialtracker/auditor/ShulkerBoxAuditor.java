package com.github.slopggobblercobbler.litematicamaterialtracker.auditor;

import com.github.slopggobblercobbler.litematicamaterialtracker.config.TrackerConfig;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Deep container inventory scanner calculating total available materials across
 * Player Inventory, nested Shulker Boxes, Bundles, and Ender Chest.
 */
public class ShulkerBoxAuditor {
    private static final ShulkerBoxAuditor INSTANCE = new ShulkerBoxAuditor();
    private static final int MAX_NESTING_DEPTH = 4;

    private InventoryAuditResult cachedResult = InventoryAuditResult.EMPTY;
    private int tickCounter = 0;

    public static ShulkerBoxAuditor getInstance() {
        return INSTANCE;
    }

    private ShulkerBoxAuditor() {}

    /**
     * Called on client tick to conditionally perform periodic audits.
     */
    public void onClientTick(MinecraftClient client) {
        if (!TrackerConfig.Generic.MOD_ENABLED.getBooleanValue()) {
            return;
        }

        if (client.player == null || client.world == null) {
            return;
        }

        this.tickCounter++;
        int interval = TrackerConfig.Auditor.AUDIT_INTERVAL_TICKS.getIntegerValue();
        if (this.tickCounter >= interval) {
            this.tickCounter = 0;
            performAudit(client);
        }
    }

    /**
     * Forces an immediate audit of player inventory and containers.
     */
    public InventoryAuditResult forceAudit(MinecraftClient client) {
        return performAudit(client);
    }

    public InventoryAuditResult getCachedResult() {
        return this.cachedResult;
    }

    private synchronized InventoryAuditResult performAudit(MinecraftClient client) {
        if (client.player == null) {
            return this.cachedResult;
        }

        Map<Item, AuditedItemCounts> map = new HashMap<>();
        AtomicInteger shulkerCount = new AtomicInteger(0);
        AtomicInteger bundleCount = new AtomicInteger(0);

        PlayerInventory playerInventory = client.player.getInventory();

        // 1. Audit Player Main Inventory and Offhand
        for (int i = 0; i < playerInventory.main.size(); i++) {
            ItemStack stack = playerInventory.main.get(i);
            auditStack(stack, map, LocationContext.PLAYER_LOOSE, 0, shulkerCount, bundleCount);
        }
        for (ItemStack stack : playerInventory.offHand) {
            auditStack(stack, map, LocationContext.PLAYER_LOOSE, 0, shulkerCount, bundleCount);
        }

        // 2. Audit Ender Chest (if enabled and available on client)
        boolean enderChestAudited = false;
        if (TrackerConfig.Auditor.ENABLE_ENDER_CHEST_AUDIT.getBooleanValue()) {
            EnderChestInventory enderChest = client.player.getEnderChestInventory();
            if (enderChest != null) {
                enderChestAudited = true;
                for (int i = 0; i < enderChest.size(); i++) {
                    ItemStack stack = enderChest.getStack(i);
                    auditStack(stack, map, LocationContext.ENDER_LOOSE, 0, shulkerCount, bundleCount);
                }
            }
        }

        this.cachedResult = new InventoryAuditResult(
                map,
                shulkerCount.get(),
                bundleCount.get(),
                enderChestAudited,
                System.currentTimeMillis()
        );

        return this.cachedResult;
    }

    private void auditStack(
            ItemStack stack,
            Map<Item, AuditedItemCounts> map,
            LocationContext context,
            int depth,
            AtomicInteger shulkerCounter,
            AtomicInteger bundleCounter) {

        if (stack == null || stack.isEmpty()) {
            return;
        }

        Item item = stack.getItem();
        boolean isShulker = isShulkerBox(stack);
        boolean isBundle = isBundle(stack);
        boolean hasContainerComponent = stack.contains(DataComponentTypes.CONTAINER);

        if (isShulker && TrackerConfig.Auditor.ENABLE_SHULKER_AUDIT.getBooleanValue()) {
            shulkerCounter.incrementAndGet();
            if (depth < MAX_NESTING_DEPTH && (depth == 0 || TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.getBooleanValue())) {
                LocationContext nestedContext = (context == LocationContext.ENDER_LOOSE || context == LocationContext.ENDER_SHULKER)
                        ? LocationContext.ENDER_SHULKER
                        : LocationContext.PLAYER_SHULKER;

                scanContainerContents(stack, map, nestedContext, depth + 1, shulkerCounter, bundleCounter);
            }
        } else if (isBundle && TrackerConfig.Auditor.AUDIT_BUNDLE_CONTENTS.getBooleanValue()) {
            bundleCounter.incrementAndGet();
            if (depth < MAX_NESTING_DEPTH && (depth == 0 || TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.getBooleanValue())) {
                scanBundleContents(stack, map, context, depth + 1, shulkerCounter, bundleCounter);
            }
        } else if (hasContainerComponent && depth < MAX_NESTING_DEPTH && TrackerConfig.Auditor.RECURSIVE_CONTAINER_SCAN.getBooleanValue()) {
            scanContainerContents(stack, map, context, depth + 1, shulkerCounter, bundleCounter);
        } else {
            // Leaf item: record quantity in the appropriate container bucket
            AuditedItemCounts counts = map.computeIfAbsent(item, k -> new AuditedItemCounts());
            int count = stack.getCount();

            switch (context) {
                case PLAYER_LOOSE:
                    counts.addLoosePlayer(count);
                    break;
                case PLAYER_SHULKER:
                    counts.addShulkerPlayer(count);
                    break;
                case ENDER_LOOSE:
                    counts.addLooseEnder(count);
                    break;
                case ENDER_SHULKER:
                    counts.addShulkerEnder(count);
                    break;
            }
        }
    }

    private void scanContainerContents(
            ItemStack containerStack,
            Map<Item, AuditedItemCounts> map,
            LocationContext context,
            int depth,
            AtomicInteger shulkerCounter,
            AtomicInteger bundleCounter) {

        ContainerComponent container = containerStack.get(DataComponentTypes.CONTAINER);
        if (container != null) {
            container.stream().forEach(subStack -> {
                if (subStack != null && !subStack.isEmpty()) {
                    auditStack(subStack, map, context, depth, shulkerCounter, bundleCounter);
                }
            });
        }
    }

    private void scanBundleContents(
            ItemStack bundleStack,
            Map<Item, AuditedItemCounts> map,
            LocationContext context,
            int depth,
            AtomicInteger shulkerCounter,
            AtomicInteger bundleCounter) {

        BundleContentsComponent bundleContents = bundleStack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (bundleContents != null) {
            bundleContents.iterate().forEach(subStack -> {
                if (subStack != null && !subStack.isEmpty()) {
                    auditStack(subStack, map, context, depth, shulkerCounter, bundleCounter);
                }
            });
        }
    }

    public static boolean isShulkerBox(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            return block instanceof ShulkerBoxBlock;
        }
        return false;
    }

    public static boolean isBundle(ItemStack stack) {
        return stack.getItem() instanceof BundleItem || stack.contains(DataComponentTypes.BUNDLE_CONTENTS);
    }

    private enum LocationContext {
        PLAYER_LOOSE,
        PLAYER_SHULKER,
        ENDER_LOOSE,
        ENDER_SHULKER
    }
}
