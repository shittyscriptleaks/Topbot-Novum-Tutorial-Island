package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

public final class SmithingGuyStep implements TutorialStep {

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

    @Override
    public boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 350;

    }

    @Override
    public void handle() {
        WidgetChild widgetChild;

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_GO_DOWN_LADDER ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_TALK_TO_MINING_GUY ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_PROSPECTED_TALK_TO_MINING_GUY ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_MADE_BAR_TALK_TO_MINING_GUY) {

            talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_PROSPECT_TIN_ORE) {
            prospectOre(new Tile(3077, 9503), S_PROSPECT_TIN_ORE);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_PROSPECT_COPPER_ORE) {
            prospectOre(new Tile(3083, 9501), S_PROSPECT_COPPER_ORE);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_MINE_TIN_ORE) {
            mineOre(new Tile(3077, 9503), S_MINE_TIN_ORE);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_MINE_COPPER_ORE) {
            mineOre(new Tile(3083, 9501), S_MINE_COPPER_ORE);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_MAKE_BAR) {
            if (!Inventory.contains("Tin ore")) {
                mineOre(new Tile(3077, 9503), S_MINE_TIN_ORE);
                return;
            }

            if (!Inventory.contains("Copper ore")) {
                mineOre(new Tile(3083, 9501), S_MINE_COPPER_ORE);
                return;
            }

            smeltBar();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_USE_BAR_ON_ANVIL) {
            useBarOnAnvil();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_SMITH_BRONZE_DAGGER && (widgetChild = Widgets.getWidgetByText("Dagger")) != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleepUntil(() -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_SMITH_BRONZE_DAGGER, 4000);
        }
    }

    private void talk() {
        if (InteractionUtil.walkTo(ENTRANCE_TILE, 10)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Mining Instructor"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    private void prospectOre(Tile tile, int setting) {
        InteractionUtil.walkToAndInteract(GameObjects.getTopAt(tile), "Prospect", () -> Settings.get(Constants.PROGRESS_SETTING_ID) != setting, 5000);
    }

    private void mineOre(Tile tile, int setting) {
        if (Players.getLocal().getAnimation() == -1) {
            InteractionUtil.walkToAndInteract(GameObjects.getTopAt(tile), "Mine", () -> Players.getLocal().getAnimation() != -1, 3000);
        }
    }

    private void smeltBar() {
        if (InteractionUtil.walkTo(new Tile(3079, 9497), 0)) {
            GameObject gameObject = GameObjects.getNearest("Furnace");
            InteractionUtil.walkToAndInteract(gameObject, () -> Inventory.useItemOn("Tin ore", gameObject), () -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_MAKE_BAR, 4000);
        }
    }

    private void useBarOnAnvil() {
        InteractionUtil.walkToAndInteract(GameObjects.getNearest("Anvil"), "Smith", () -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_USE_BAR_ON_ANVIL, 5000);
    }

}

