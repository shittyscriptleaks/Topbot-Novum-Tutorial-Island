package deob;

import org.tbot.bot.TBot;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.Game;
import org.tbot.methods.Mouse;
import org.tbot.methods.Random;
import org.tbot.methods.Time;
import org.tbot.methods.walking.Walking;
import org.tbot.methods.web.path.WebPath;
import org.tbot.util.Condition;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Interactable;
import org.tbot.wrappers.Locatable;

public final class Util {

    private Util() { }

    private static int toggleRunThreshold = 40;
    public static final Condition CAN_CONTINUE_DIALOG_COND = new CanContinueDialogCondition();

    public static void walkToAndInteract(Locatable locatable, Condition condition, Condition condition2, int breakTime) {
        Util.walkToAndInteract(locatable, condition, condition2, breakTime, !(locatable instanceof GameObject));
    }

    public static void walkToAndInteract(Locatable locatable, String action, Condition condition, int breakTime, boolean canBeFarAway) {
        Util.walkToAndInteract(locatable, new InteractWithLocatableCondition(locatable, action), condition, breakTime, canBeFarAway);
    }

    public static void handleRunToggle() {
        if (!Walking.isRunEnabled() && Walking.getRunEnergy() >= toggleRunThreshold && Walking.setRun(true)) {
            toggleRunThreshold = Random.nextInt(20, 50);
            Time.sleepUntil(new IsRunEnabledCondition(), Random.nextInt(800, 1200));
        }
    }

    public static void walkToAndInteract(Locatable locatable, String action, Condition condition, int breakTime) {
        Util.walkToAndInteract(locatable, action, condition, breakTime, !(locatable instanceof GameObject));
    }

    public static void walkToAndInteract(Locatable locatable, Condition interactCondition, Condition condition, int breakTime, boolean canBeFarAway) {
        if (locatable != null && ((Interactable)locatable).isOnScreen() && (Game.hasSpellselected() || !canBeFarAway || Walking.canReach(locatable.getLocation()))) {
            if (TimeMap.validate(0, 1800) && interactCondition.check()) {
                if (Mouse.getClickState() == 2) {
                    Time.sleepUntil(condition, breakTime);
                }

                TimeMap.update(0);
            }
            return;
        }

        Util.walkToLocatable(locatable, 1);
    }

    public static boolean walkToLocatable(Locatable locatable, int dist) {
        Util.handleRunToggle();

        if (locatable == null) {
            return false;
        }

        if (locatable.distance() <= dist) {
            return true;
        }

        if (Util.shouldContinueWalking(locatable)) {
            WebPath webPath = TBot.getBot().getDefaultWeb().findPath(locatable.getLocation());
            if (webPath != null) {
                Time.sleepUntil(new HasDestinationCondition(), 1200);
                webPath.traverse();
                if (Walking.getDestination() == null && !Walking.isMoving() && locatable.getLocation().isOnMiniMap()) {
                    Walking.walkTileMM(locatable.getLocation());
                }
            } else {
                LogHandler.log(new StringBuilder().insert(0, WebUtil.decryptStringStub("M2`=a'.$o?esz<4s")).append(locatable.getLocation()).toString());
            }
        }
        return false;
    }

    public static boolean shouldContinueWalking(Locatable locatable) {
        if (Walking.getDestination() != null) {
            int distance = locatable.distance(Walking.getDestination());
            if (!(Walking.isMoving() && distance <= 2)) {
                return true;
            }
            return false;
        }

        if (!Walking.isMoving()) {
            return true;
        }

        return false;
    }

}

