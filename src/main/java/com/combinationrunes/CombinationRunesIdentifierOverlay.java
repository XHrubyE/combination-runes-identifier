package com.combinationrunes;

import java.awt.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

@Singleton
public class CombinationRunesIdentifierOverlay extends WidgetItemOverlay {

    private final static String GAP = "     ";
    private final static int COORDINATE_OFFSET_X = 2;
    private final static int COORDINATE_OFFSET_Y = 2;
    private static final int WIDGET_ID_COMPONENT_BITS = 16;

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
        if (!showOverlay(widgetItem)) {
            return;
        }

        CombinationRune rune = CombinationRune.of(itemManager.canonicalize(itemId));

        if (rune == null) {
            return;
        }

        drawLetters(graphics, widgetItem.getCanvasBounds(), rune);
    }

    private boolean showOverlay(final WidgetItem widgetItem) {
        if (!config.showOverlay()) {
            return false;
        }

        if (!config.showInInventory() && isInventoryItem(widgetItem)) {
            return false;
        }

        return true;
    }

    private boolean isInventoryItem(final WidgetItem widgetItem) {
        Widget widget = widgetItem.getWidget();

        if (widget == null) {
            return false;
        }

        int groupId = widget.getId() >>> WIDGET_ID_COMPONENT_BITS;
        return groupId == InterfaceID.INVENTORY;
    }

    private void drawLetters(Graphics2D graphics, Rectangle canvasBounds, CombinationRune rune) {
        graphics.setFont(FontManager.getRunescapeSmallFont());
        FontMetrics fontMetrics = graphics.getFontMetrics();

        String firstElementSymbol = rune.getFirstElement().getSymbol();
        String secondElementSymbol = rune.getSecondElement().getSymbol();

        int totalWidth = fontMetrics.stringWidth(firstElementSymbol + GAP + secondElementSymbol);
        int x = canvasBounds.x + ((canvasBounds.width - totalWidth) / 2) - COORDINATE_OFFSET_X;
        int y = canvasBounds.y + canvasBounds.height - COORDINATE_OFFSET_Y;

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
