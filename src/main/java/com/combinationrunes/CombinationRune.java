package com.combinationrunes;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import net.runelite.api.ItemID;

public enum CombinationRune {
    MIST(ItemID.MIST_RUNE, ElementalRune.AIR, ElementalRune.WATER),
    DUST(ItemID.DUST_RUNE, ElementalRune.AIR, ElementalRune.EARTH),
    MUD(ItemID.MUD_RUNE, ElementalRune.WATER, ElementalRune.EARTH),
    SMOKE(ItemID.SMOKE_RUNE, ElementalRune.AIR, ElementalRune.FIRE),
    STEAM(ItemID.STEAM_RUNE, ElementalRune.WATER, ElementalRune.FIRE),
    LAVA(ItemID.LAVA_RUNE, ElementalRune.EARTH, ElementalRune.FIRE);

    @Getter
    private final int itemId;
    @Getter
    private final ElementalRune firstElement;
    @Getter
    private final ElementalRune secondElement;

    CombinationRune(final int itemId, final ElementalRune firstElement, final ElementalRune secondElement) {
        this.itemId = itemId;
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    private static final Map<Integer, CombinationRune> BY_ID = new HashMap<>();

    static {
        for (CombinationRune r : values()) {
            BY_ID.put(r.itemId, r);
        }
    }

    static CombinationRune of(int id) {
        return BY_ID.get(id);
    }
}
