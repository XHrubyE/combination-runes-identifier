package com.combinationrunes;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Combination Runes Identifier"
)
public class CombinationRunesIdentifierPlugin extends Plugin {

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private CombinationRunesIdentifierOverlay combinationRunesOverlay;

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(combinationRunesOverlay);
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(combinationRunesOverlay);
    }

    @SuppressWarnings("unused")
    @Provides
    CombinationRunesIdentifierConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(CombinationRunesIdentifierConfig.class);
    }
}
