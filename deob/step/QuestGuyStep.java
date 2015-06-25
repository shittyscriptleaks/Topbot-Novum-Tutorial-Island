package deob.step;

import deob.util.Util;
import deob.condition.LocalPerformingAnimationCondition;
import org.tbot.methods.Npcs;
import org.tbot.methods.Players;
import org.tbot.methods.Settings;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.methods.tabs.Emotes;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.Tile;

public final class QuestGuyStep {

    private QuestGuyStep() { }

    private static final Tile ENTRACE_TILE = new Tile(3085, 3122);;

    private static final int S_OPEN_EMOTE_TAB = 183;
    private static final int S_DO_EMOTE = 187;
    private static final int S_LEAVE_COOK_BUILDING = 180;
    private static final int S_OPEN_SETTINGS = 190;
    private static final int S_ENABLE_RUN = 200;
    private static final int S_OPEN_QUEST_DOOR = 210;
    private static final int S_TALK_TO_QUEST_GUY = 220;
    private static final int S_OPEN_QUEST_TAB = 230;
    private static final int S_QUEST_TAB_OPEN_TALK_TO_QUEST_GUY = 240;

    public static void handle() {
        if (Settings.get(281) == S_OPEN_EMOTE_TAB) {
            Widgets.openTab(12);
        }

        if (Settings.get(281) == S_DO_EMOTE && Players.getLocal().getAnimation() == -1 && Emotes.doEmote(Emotes.Emote.DANCE)) {
            Time.sleepUntil(new LocalPerformingAnimationCondition());
        }

        if (Settings.get(281) == S_OPEN_SETTINGS) {
            Widgets.openTab(11);
        }

        if (Settings.get(281) == S_ENABLE_RUN) {
            Time.sleep(600, 1200);
            Walking.setRun(!Walking.isRunEnabled());
        }

        if (Settings.get(281) == S_LEAVE_COOK_BUILDING ||
                Settings.get(281) == S_OPEN_QUEST_DOOR ||
                Settings.get(281) == S_TALK_TO_QUEST_GUY ||
                Settings.get(281) == S_QUEST_TAB_OPEN_TALK_TO_QUEST_GUY) {

            QuestGuyStep.talk();
        }

        if (Settings.get(281) == S_OPEN_QUEST_TAB) {
            Widgets.openTab(2);
        }
    }

    public static boolean hasProgressedPast() {
        if (Settings.get(281) > 240) {
            return true;
        }

        return false;
    }

    private static void talk() {
        if (Util.walkToLocatable(ENTRACE_TILE, 3)) {
            Util.walkToAndInteract(Npcs.getNearest("Quest Guide"), "Talk-to", Util.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

