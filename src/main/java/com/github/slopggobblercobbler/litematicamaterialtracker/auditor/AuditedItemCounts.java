package com.github.slopggobblercobbler.litematicamaterialtracker.auditor;

public class AuditedItemCounts {
    private int loosePlayer;
    private int shulkerPlayer;
    private int looseEnder;
    private int shulkerEnder;

    public AuditedItemCounts() {
        this.loosePlayer = 0;
        this.shulkerPlayer = 0;
        this.looseEnder = 0;
        this.shulkerEnder = 0;
    }

    public void addLoosePlayer(int amount) {
        this.loosePlayer += amount;
    }

    public void addShulkerPlayer(int amount) {
        this.shulkerPlayer += amount;
    }

    public void addLooseEnder(int amount) {
        this.looseEnder += amount;
    }

    public void addShulkerEnder(int amount) {
        this.shulkerEnder += amount;
    }

    public int getLoosePlayer() {
        return this.loosePlayer;
    }

    public int getShulkerPlayer() {
        return this.shulkerPlayer;
    }

    public int getLooseEnder() {
        return this.looseEnder;
    }

    public int getShulkerEnder() {
        return this.shulkerEnder;
    }

    public int getTotalInPlayerInventory() {
        return this.loosePlayer + this.shulkerPlayer;
    }

    public int getTotalInEnderChest() {
        return this.looseEnder + this.shulkerEnder;
    }

    public int getTotalInShulkers() {
        return this.shulkerPlayer + this.shulkerEnder;
    }

    public int getTotalAvailable() {
        return this.loosePlayer + this.shulkerPlayer + this.looseEnder + this.shulkerEnder;
    }
}
