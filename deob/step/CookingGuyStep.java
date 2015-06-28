package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Tile;

public final class CookingGuyStep implements TutorialStep {

    private static final Tile ENTRANCE_TILE = new Tile(3075, 3084);

    private static final int S_OPEN_WOOD_GATE = 120;
    private static final int S_OPEN_DOOR = 130;
    private static final int S_TALK_TO_COOK = 140;
    private static final int S_COMBINE_FLOUR_AND_WATER = 150;
    private static final int S_COOK_BREAD = 160;
    private static final int S_OPEN_MUSIC_TAB = 170;

    @Override
    public boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 170;

    }

    @Override
    public void handle() {
        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_WOOD_GATE || Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_DOOR || Settings.get(Constants.PROGRESS_SETTING_ID) == S_TALK_TO_COOK) {
            talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_COMBINE_FLOUR_AND_WATER) {
            combineIngrediants();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_COOK_BREAD) {
            cookBread();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_MUSIC_TAB) {
            Widgets.openTab(13);
        }
    }

    private void talk() {
        if (InteractionUtil.walkTo(ENTRANCE_TILE, 3)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Master Chef"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    private void combineIngrediants() {
        if (Inventory.getFirst("Pot of flour") == null) {
            talk();
            return;
        }

        if (Inventory.getFirst("Bucket of water") == null) {
            talk();
            return;
        }

        if (Inventory.useItemOn("Pot of flour", "Bucket of water")) {
            Time.sleepUntil(() -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_COMBINE_FLOUR_AND_WATER, 3000);
        }
    }

    private void cookBread() {
        Time.sleep(300, 600);
        if (Inventory.contains("Bread dough")) {
            if (Inventory.useItemOn("Bread dough", GameObjects.getNearest("Range"))) {
                Time.sleepUntil(() -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_COOK_BREAD, 5000);
            }
        } else {
            combineIngrediants();
        }
    }

}

