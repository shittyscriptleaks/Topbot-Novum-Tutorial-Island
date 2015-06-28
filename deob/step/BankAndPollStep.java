package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Settings;
import org.tbot.wrappers.Tile;

public final class BankAndPollStep implements TutorialStep {

    private static final Tile ENTRANCE_TILE = new Tile(3121, 3121);

    private static final int S_GO_UP_LADDER = 500;
    private static final int S_USE_BANK = 510;
    private static final int S_USE_POLL = 520;

    @Override
    public boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 520;

    }

    @Override
    public void handle() {
        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_GO_UP_LADDER) {
            InteractionUtil.walkTo(ENTRANCE_TILE, 6);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_USE_BANK) {
            InteractionUtil.walkToAndInteract(GameObjects.getNearest("Bank booth"), "Use", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_USE_POLL) {
            InteractionUtil.walkToAndInteract(GameObjects.getNearest("Poll booth"), "Use", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

