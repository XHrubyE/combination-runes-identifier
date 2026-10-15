package com.combinationrunes;

import java.awt.*;

import lombok.Getter;
import net.runelite.api.ItemID;

public enum ElementalRune {
    AIR(ItemID.AIR_RUNE, "A", new Color(220, 240, 255)),
    WATER(ItemID.WATER_RUNE, "W", new Color(25, 87, 245)),
    EARTH(ItemID.EARTH_RUNE, "E", new Color(0, 129, 32)),
    FIRE(ItemID.FIRE_RUNE, "F", new Color(212, 56, 3));

    @Getter
    private final int itemId;
    @Getter
    private final String symbol;
    @Getter
    private final Color color;

    ElementalRune(final int itemId, final String symbol, final Color color) {
        this.itemId = itemId;
        this.symbol = symbol;
        this.color = color;
    }
}

