package com.combinationrunes;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("combinationrunes")
public interface CombinationRunesIdentifierConfig extends Config {

    String SECTION_DESCRIPTION_GENERAL = "Combination Runes Identifier settings";

    String ITEM_DESCRIPTION_SHOW_OVERLAY = "Enable/Disable overlay on combination runes";
    String ITEM_DESCRIPTION_COLORED_SYMBOLS = "Enable/Disable color for elemental rune symbols";
    String ITEM_DESCRIPTION_SHOW_IN_INVENTORY = "Enable/Disable overlay in inventory";

    @ConfigSection(name = "General", description = SECTION_DESCRIPTION_GENERAL, position = 0)
    String generalSection = "generalSection";

    @ConfigItem(keyName = "showOverlay", name = "Show overlay", description = ITEM_DESCRIPTION_SHOW_OVERLAY, position = 0, section = generalSection)
    default boolean showOverlay() {
        return true;
    }

    @ConfigItem(keyName = "coloredSymbols", name = "Colored symbols", description = ITEM_DESCRIPTION_COLORED_SYMBOLS, position = 1,
            section = generalSection)
    default boolean coloredSymbols() {
        return true;
    }

    @ConfigItem(keyName = "showInInventory", name = "Show overlay in inventory", description = ITEM_DESCRIPTION_SHOW_IN_INVENTORY, position = 2,
            section = generalSection)
    default boolean showInInventory() {
        return false;
    }
}
