package deob.util;

import org.tbot.bot.TBot;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.*;
import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.path.WebPath;
import org.tbot.util.Condition;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Interactable;
import org.tbot.wrappers.Locatable;

public final class InteractionUtil {

    private InteractionUtil() { }

    private static int toggleRunThreshold = 40;
    public static final Condition CAN_CONTINUE_DIALOG_COND = Widgets::canContinue;

    public static void walkToAndInteract(Locatable locatable, Condition condition, Condition condition2, int breakTime) {
        InteractionUtil.walkToAndInteract(locatable, condition, condition2, breakTime, !(locatable instanceof GameObject));
    }

    public static void walkToAndInteract(Locatable locatable, String action, Condition condition, int breakTime, boolean canBeFarAway) {
        InteractionUtil.walkToAndInteract(locatable, () -> ((Interactable) locatable).interact(action), condition, breakTime, canBeFarAway);
    }

    public static void handleRunToggle() {
        if (!Walking.isRunEnabled() && Walking.getRunEnergy() >= toggleRunThreshold && Walking.setRun(true)) {
            toggleRunThreshold = Random.nextInt(20, 50);
            Time.sleepUntil(Walking::isRunEnabled, Random.nextInt(800, 1200));
        }
    }

    public static void walkToAndInteract(Locatable locatable, String action, Condition condition, int breakTime) {
        InteractionUtil.walkToAndInteract(locatable, action, condition, breakTime, !(locatable instanceof GameObject));
    }

    public static void walkToAndInteract(Locatable locatable, Condition interactCondition, Condition condition, int breakTime, boolean canBeFarAway) {
        if (locatable != null && ((Interactable)locatable).isOnScreen() && (Game.hasSpellselected() || !canBeFarAway || Walking.canReach(locatable.getLocation()))) {
            if (TimerMap.validate(0, 1800) && interactCondition.check()) {
                if (Mouse.getClickState() == 2) {
                    Time.sleepUntil(condition, breakTime);
                }

                TimerMap.update(0);
            }
            return;
        }

        InteractionUtil.walkToLocatable(locatable, 1);
    }

    public static boolean walkToLocatable(Locatable locatable, int dist) {
        InteractionUtil.handleRunToggle();

        if (locatable == null) {
            return false;
        }

        if (locatable.distance() <= dist) {
            return true;
        }

        if (InteractionUtil.shouldContinueWalking(locatable)) {
            WebPath webPath = TBot.getBot().getDefaultWeb().findPath(locatable.getLocation());
            if (webPath != null) {
                Time.sleepUntil(() -> Walking.getDestination() != null, 1200);
                webPath.traverse();
                if (Walking.getDestination() == null && !Walking.isMoving() && locatable.getLocation().isOnMiniMap()) {
                    Walking.walkTileMM(locatable.getLocation());
                }
            } else {
                LogHandler.log("Cannot walk to: " + locatable.getLocation().toString());
            }
        }
        return false;
    }

    public static boolean shouldContinueWalking(Locatable locatable) {
        if (Walking.getDestination() != null) {
            int distance = locatable.distance(Walking.getDestination());
            return !(Walking.isMoving() && distance <= 2);
        }

        return !Walking.isMoving();

    }

}

