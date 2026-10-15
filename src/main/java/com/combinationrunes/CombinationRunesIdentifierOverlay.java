package com.combinationrunes;

import java.awt.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

@Singleton
public class CombinationRunesIdentifierOverlay extends WidgetItemOverlay {

    private final static String GAP = "     ";

    private final CombinationRunesIdentifierConfig config;
    private final ItemManager itemManager;

    @Inject
    CombinationRunesIdentifierOverlay(CombinationRunesIdentifierConfig config, ItemManager itemManager) {
        this.config = config;
        this.itemManager = itemManager;
        showOnBank();
        showOnInventory();
    }

    @Override
    public void renderItemOverlay(final Graphics2D graphics, final int itemId, final WidgetItem widgetItem) {
        if (!config.showOverlay()) {
            return;
        }

        CombinationRune rune = CombinationRune.of(itemManager.canonicalize(itemId));

        if (rune == null) {
            return;
        }

        drawLetters(graphics, widgetItem.getCanvasBounds(), rune);
    }

    private void drawLetters(Graphics2D graphics, Rectangle canvasBounds, CombinationRune rune) {
        graphics.setFont(FontManager.getRunescapeSmallFont());
        FontMetrics fontMetrics = graphics.getFontMetrics();

        String firstElementSymbol = rune.getFirstElement().getSymbol();
        String secondElementSymbol = rune.getSecondElement().getSymbol();

        int totalWidth = fontMetrics.stringWidth(firstElementSymbol + GAP + secondElementSymbol);
        int x = canvasBounds.x + (canvasBounds.width - totalWidth) / 2;
        int y = canvasBounds.y + canvasBounds.height - 2;

        drawShadowed(graphics, firstElementSymbol, x, y, rune.getFirstElement().getColor());
        x += fontMetrics.stringWidth(firstElementSymbol + GAP);
        drawShadowed(graphics, secondElementSymbol, x, y, rune.getSecondElement().getColor());
    }

    private void drawShadowed(Graphics2D graphics, String text, int x, int y, Color color) {
        graphics.setColor(Color.BLACK);
        graphics.drawString(text, x + 1, y + 1);

        graphics.setColor(config.coloredSymbols() ? color : Color.WHITE);
        graphics.drawString(text, x, y);
    }
}
