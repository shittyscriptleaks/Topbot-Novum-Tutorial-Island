package deob.step;

import deob.util.InteractionUtil;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

public final class SmithingGuyStep {

    private SmithingGuyStep() { }

    private static final Tile ENTRANCE_TILE = new Tile(3080, 9501);

    private static final int S_GO_DOWN_LADDER = 250;
    private static final int S_TALK_TO_MINING_GUY = 260;

    private static final int S_PROSPECT_TIN_ORE = 270;
    private static final int S_PROSPECT_COPPER_ORE = 280;
    private static final int S_PROSPECTED_TALK_TO_MINING_GUY = 290;

    private static final int S_MINE_TIN_ORE = 300;
    private static final int S_MINE_COPPER_ORE = 310;
    private static final int S_MAKE_BAR = 320;
    private static final int S_MADE_BAR_TALK_TO_MINING_GUY = 330;

    private static final int S_USE_BAR_ON_ANVIL = 340;

    private static final int S_SMITH_BRONZE_DAGGER = 350;

    public static boolean hasProgressedPast() {
        return Settings.get(281) > 350;

    }

    public static void handle() {
        WidgetChild widgetChild;

        if (Settings.get(281) == S_GO_DOWN_LADDER ||
                Settings.get(281) == S_TALK_TO_MINING_GUY ||
                Settings.get(281) == S_PROSPECTED_TALK_TO_MINING_GUY ||
                Settings.get(281) == S_MADE_BAR_TALK_TO_MINING_GUY) {

            SmithingGuyStep.talk();
        }

        if (Settings.get(281) == S_PROSPECT_TIN_ORE) {
            SmithingGuyStep.prospectOre(new Tile(3077, 9503), S_PROSPECT_TIN_ORE);
        }

        if (Settings.get(281) == S_PROSPECT_COPPER_ORE) {
            SmithingGuyStep.prospectOre(new Tile(3083, 9501), S_PROSPECT_COPPER_ORE);
        }

        if (Settings.get(281) == S_MINE_TIN_ORE) {
            SmithingGuyStep.mineOre(new Tile(3077, 9503), S_MINE_TIN_ORE);
        }

        if (Settings.get(281) == S_MINE_COPPER_ORE) {
            SmithingGuyStep.mineOre(new Tile(3083, 9501), S_MINE_COPPER_ORE);
        }

        if (Settings.get(281) == S_MAKE_BAR) {
            if (!Inventory.contains("Tin ore")) {
                SmithingGuyStep.mineOre(new Tile(3077, 9503), S_MINE_TIN_ORE);
                return;
            }

            if (!Inventory.contains("Copper ore")) {
                SmithingGuyStep.mineOre(new Tile(3083, 9501), S_MINE_COPPER_ORE);
                return;
            }

            SmithingGuyStep.smeltBar();
        }

        if (Settings.get(281) == S_USE_BAR_ON_ANVIL) {
            SmithingGuyStep.useBarOnAnvil();
        }

        if (Settings.get(281) == S_SMITH_BRONZE_DAGGER && (widgetChild = Widgets.getWidgetByText("Dagger")) != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleepUntil(() -> Settings.get(281) != S_SMITH_BRONZE_DAGGER, 4000);
        }
    }

    private static void talk() {
        if (InteractionUtil.walkToLocatable(ENTRANCE_TILE, 10)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Mining Instructor"), "Talk-to", InteractionUtil.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    private static void prospectOre(Tile tile, int setting) {
        InteractionUtil.walkToAndInteract(GameObjects.getTopAt(tile), "Prospect", () -> Settings.get(281) != setting, 5000);
    }

    private static void mineOre(Tile tile, int setting) {
        if (Players.getLocal().getAnimation() == -1) {
            InteractionUtil.walkToAndInteract(GameObjects.getTopAt(tile), "Mine", () -> Players.getLocal().getAnimation() != -1, 3000);
        }
    }

    private static void smeltBar() {
        if (InteractionUtil.walkToLocatable(new Tile(3079, 9497), 0)) {
            GameObject gameObject = GameObjects.getNearest("Furnace");
            InteractionUtil.walkToAndInteract(gameObject, () -> Inventory.useItemOn("Tin ore", gameObject), () -> Settings.get(281) != S_MAKE_BAR, 4000);
        }
    }

    private static void useBarOnAnvil() {
        InteractionUtil.walkToAndInteract(GameObjects.getNearest("Anvil"), "Smith", () -> Settings.get(281) != S_USE_BAR_ON_ANVIL, 5000);
    }

}

