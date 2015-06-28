package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Emotes;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.Tile;

public final class QuestGuyStep implements TutorialStep {

    private static final Tile ENTRACE_TILE = new Tile(3085, 3122);

    private static final int S_OPEN_EMOTE_TAB = 183;
    private static final int S_DO_EMOTE = 187;
    private static final int S_LEAVE_COOK_BUILDING = 180;
    private static final int S_OPEN_SETTINGS = 190;
    private static final int S_ENABLE_RUN = 200;
    private static final int S_OPEN_QUEST_DOOR = 210;
    private static final int S_TALK_TO_QUEST_GUY = 220;
    private static final int S_OPEN_QUEST_TAB = 230;
    private static final int S_QUEST_TAB_OPEN_TALK_TO_QUEST_GUY = 240;

    @Override
    public boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 240;

    }

    @Override
    public void handle() {
        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_EMOTE_TAB) {
            Widgets.openTab(12);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_DO_EMOTE && Players.getLocal().getAnimation() == -1 && Emotes.doEmote(Emotes.Emote.DANCE)) {
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_SETTINGS) {
            Widgets.openTab(11);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_ENABLE_RUN) {
            Time.sleep(600, 1200);
            Walking.setRun(!Walking.isRunEnabled());
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_LEAVE_COOK_BUILDING ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_QUEST_DOOR ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_TALK_TO_QUEST_GUY ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_QUEST_TAB_OPEN_TALK_TO_QUEST_GUY) {

            talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_QUEST_TAB) {
            Widgets.openTab(2);
        }
    }

    private void talk() {
        if (InteractionUtil.walkTo(ENTRACE_TILE, 3)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Quest Guide"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

