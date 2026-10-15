package com.combinationrunes;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("combinationrunes")
public interface CombinationRunesIdentifierConfig extends Config {
    @ConfigSection(name = "General", description = "Combination Runes Identifier settings", position = 0)
    String generalSection = "generalSection";

    @ConfigItem(keyName = "showOverlay", name = "Show overlay", description = "Enable/Disable overlay on combination runes", position = 0,
            section = generalSection)
    default boolean showOverlay() {
        return true;
    }

    @ConfigItem(keyName = "coloredSymbols", name = "Colored symbols", description = "Enable/Disable color for elemental rune symbols", position = 1,
            section = generalSection)
    default boolean coloredSymbols() {
        return true;
    }
}
