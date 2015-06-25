package deob.step;

import deob.util.Util;
import org.tbot.methods.Npcs;
import org.tbot.methods.Settings;
import org.tbot.methods.Widgets;
import org.tbot.wrappers.Tile;

public final class BrotherBraceStep {

    private BrotherBraceStep() { }

    private static final Tile ENTRACE_TILE = new Tile(3125, 3107);

    private static final int S_CONTINUE_THROUGH_DOOR = 540;
    private static final int S_TALK_TO_BROTHER = 550;
    private static final int S_OPEN_PRAYER_TAB = 560;
    private static final int S_PRAYER_OPEN_TALK_TO_BROTHER = 570;
    private static final int S_OPEN_FRIENDS_LIST = 580;
    private static final int S_OPEN_IGNORE_LIST = 590;
    private static final int S_SOCIAL_OPEN_TALK_TO_BROTHER = 600;

    public static boolean hasProgressedPast() {
        if (Settings.get(281) > 600) {
            return true;
        }

        return false;
    }

    public static void handle() {
        if (Settings.get(281) == S_CONTINUE_THROUGH_DOOR ||
                Settings.get(281) == S_TALK_TO_BROTHER ||
                Settings.get(281) == S_PRAYER_OPEN_TALK_TO_BROTHER ||
                Settings.get(281) == S_SOCIAL_OPEN_TALK_TO_BROTHER) {

            BrotherBraceStep.talkToBrother();
        }

        if (Settings.get(281) == S_OPEN_PRAYER_TAB) {
            Widgets.openTab(5);
        }
        if (Settings.get(281) == S_OPEN_FRIENDS_LIST) {
            Widgets.openTab(8);
        }
        if (Settings.get(281) == S_OPEN_IGNORE_LIST) {
            Widgets.openTab(9);
        }
    }

    private static void talkToBrother() {
        if (Npcs.getNearest("Brother Brace") != null) {
            if (Util.walkToLocatable(ENTRACE_TILE, 7)) {
                Util.walkToAndInteract(Npcs.getNearest("Brother Brace"), "Talk-to", Util.CAN_CONTINUE_DIALOG_COND, 3000);
            }
        }
    }

}

