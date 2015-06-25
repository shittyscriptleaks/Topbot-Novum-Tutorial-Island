package deob.step;

import deob.util.Util;
import deob.condition.IsMakingUncookedBreadCondition;
import deob.condition.IsNotMakingBreadCondition;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Npcs;
import org.tbot.methods.Settings;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Tile;

public final class CookingGuyStep {

    private CookingGuyStep() { }

    private static final Tile ENTRANCE_TILE = new Tile(3075, 3084);

    private static final int S_OPEN_WOOD_GATE = 120;
    private static final int S_OPEN_DOOR = 130;
    private static final int S_TALK_TO_COOK = 140;
    private static final int S_COMBINE_FLOUR_AND_WATER = 150;
    private static final int S_COOK_BREAD = 160;
    private static final int S_OPEN_MUSIC_TAB = 170;

    public static boolean hasProgressedPast() {
        if (Settings.get(281) > 170) {
            return true;
        }

        return false;
    }

    public static void handle() {
        if (Settings.get(281) == S_OPEN_WOOD_GATE || Settings.get(281) == S_OPEN_DOOR || Settings.get(281) == S_TALK_TO_COOK) {
            CookingGuyStep.talk();
        }

        if (Settings.get(281) == S_COMBINE_FLOUR_AND_WATER) {
            CookingGuyStep.combineIngrediants();
        }

        if (Settings.get(281) == S_COOK_BREAD) {
            CookingGuyStep.cookBread();
        }

        if (Settings.get(281) == S_OPEN_MUSIC_TAB) {
            Widgets.openTab(13);
        }
    }

    private static void talk() {
        if (Util.walkToLocatable(ENTRANCE_TILE, 3)) {
            Util.walkToAndInteract(Npcs.getNearest("Master Chef"), "Talk-to", Util.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    private static void combineIngrediants() {
        if (Inventory.getFirst("Pot of flour") == null) {
            CookingGuyStep.talk();
            return;
        }

        if (Inventory.getFirst("Bucket of water") == null) {
            CookingGuyStep.talk();
            return;
        }

        if (Inventory.useItemOn("Pot of flour", "Bucket of water")) {
            Time.sleepUntil(new IsMakingUncookedBreadCondition(), 3000);
        }
    }

    private static void cookBread() {
        Time.sleep(300, 600);
        if (Inventory.contains("Bread dough")) {
            if (Inventory.useItemOn("Bread dough", GameObjects.getNearest("Range"))) {
                Time.sleepUntil(new IsNotMakingBreadCondition(), 5000);
            }
        } else {
            CookingGuyStep.combineIngrediants();
        }
    }

}

