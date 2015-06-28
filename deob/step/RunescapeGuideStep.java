package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.Npcs;
import org.tbot.methods.Settings;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.wrappers.Tile;

public final class RunescapeGuideStep implements TutorialStep {

    private static final Tile ENTRANCE_TILE = new Tile(3094, 3107, 0);

    // not sure about these, cbf to check
    private static final int[] LEFT_IDS = new int[]{ 113, 114, 115, 116, 117, 118, 119 };
    private static final int[] RIGHT_IDS = new int[]{ 121, 127, 129, 130, 131 };

    private static final int S_INITIAL_TALK_TO_GUIDE = 0;
    private static final int S_OPEN_SETTINGS = 3;
    private static final int S_SETTINGS_OPEN_TALK_TO_GUIDE = 7;
    private static final int S_LEAVE_THROUGH_DOOR = 10;

    private static final int WIDGET_ROOT_MAKEOVER_MAGE = 269;

    @Override
    public boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 10;

    }

    @Override
    public void handle() {
        if (handleMakeoverInterface()) {
            return;
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_INITIAL_TALK_TO_GUIDE || Settings.get(Constants.PROGRESS_SETTING_ID) == S_SETTINGS_OPEN_TALK_TO_GUIDE) {
            talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_SETTINGS) {
            Widgets.openTab(11);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_LEAVE_THROUGH_DOOR) {
            InteractionUtil.walkTo(new Tile(3102, 3096), 4);
        }
    }

    private void randomizeAppearance(int... widgetIds) {
        for (int id : widgetIds) {
            if (Math.random() >= 0.3) {
                double threshold = Math.random() * 10.0D;
                for (double d = 0; d < threshold; d++) {
                    Time.sleep(100, 300);
                    Widgets.getWidget(WIDGET_ROOT_MAKEOVER_MAGE, id).click();
                }
            }
        }
    }

    private boolean handleMakeoverInterface() {
        if (Widgets.getWidget(269, 113).isVisible()) {
            randomizeAppearance(LEFT_IDS);
            randomizeAppearance(RIGHT_IDS);

            if (Widgets.getWidget(269, 99).click()) {
                Time.sleepUntil(() -> !Widgets.getWidget(269, 113).isVisible());
            }
            return true;
        }

        return false;
    }

    private void talk() {
        if (InteractionUtil.walkTo(ENTRANCE_TILE, 5)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Runescape Guide"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

