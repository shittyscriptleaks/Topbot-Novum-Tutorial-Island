package deob.step;

import deob.util.InteractionUtil;
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
        return Settings.get(281) > 600;

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
        if (InteractionUtil.walkToLocatable(ENTRACE_TILE, 7)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Brother Brace"), "Talk-to", InteractionUtil.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

