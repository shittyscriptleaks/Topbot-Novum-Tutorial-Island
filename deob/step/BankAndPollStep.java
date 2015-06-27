package deob.step;

import deob.util.InteractionUtil;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Settings;
import org.tbot.wrappers.Tile;

public final class BankAndPollStep {

    private BankAndPollStep() { }

    private static final Tile ENTRANCE_TILE = new Tile(3121, 3121);

    private static final int S_GO_UP_LADDER = 500;
    private static final int S_USE_BANK = 510;
    private static final int S_USE_POLL = 520;

    public static boolean hasProgressedPast() {
        return Settings.get(281) > 520;

    }

    public static void handle() {
        if (Settings.get(281) == S_GO_UP_LADDER) {
            InteractionUtil.walkToLocatable(ENTRANCE_TILE, 6);
        }

        if (Settings.get(281) == S_USE_BANK) {
            InteractionUtil.walkToAndInteract(GameObjects.getNearest("Bank booth"), "Use", InteractionUtil.CAN_CONTINUE_DIALOG_COND, 3000);
        }

        if (Settings.get(281) == S_USE_POLL) {
            InteractionUtil.walkToAndInteract(GameObjects.getNearest("Poll booth"), "Use", InteractionUtil.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

