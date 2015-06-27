package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.Npcs;
import org.tbot.methods.Settings;
import org.tbot.methods.Widgets;
import org.tbot.methods.combat.magic.Magic;
import org.tbot.methods.combat.magic.SpellBooks;
import org.tbot.wrappers.Tile;

public final class MagicInstructorStep {

    private MagicInstructorStep() { }

    private static final Tile ENTRANCE_TILE = new Tile(3141, 3087);

    private static final int S_LEAVE_MONESTRY = 610;
    private static final int S_TALK_TO_MAGIC_INSTRUCTOR = 620;
    private static final int S_OPEN_MAGIC_TAB = 630;
    private static final int S_MAGIC_TAB_OPEN_TALK_TO_MAGIC_INSTRUCTOR = 640;
    private static final int S_WIND_STRIKE_CHICKEN = 650;
    private static final int S_LEAVE_TALK_TO_MAGIC_INSTRUCTOR = 670;

    public static boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) >= 1000;

    }

    public static void handle() {
        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_LEAVE_MONESTRY ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_TALK_TO_MAGIC_INSTRUCTOR ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_MAGIC_TAB_OPEN_TALK_TO_MAGIC_INSTRUCTOR ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_LEAVE_TALK_TO_MAGIC_INSTRUCTOR) {

            if (Magic.hasSpellSelected()) {
                Magic.deselectSpell();
            }

            MagicInstructorStep.talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_MAGIC_TAB) {
            Widgets.openTab(6);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_WIND_STRIKE_CHICKEN &&
                InteractionUtil.walkToLocatable(new Tile(3140, 3090), 0) &&
                (Magic.getSelectedSpell() == SpellBooks.Modern.WIND_STRIKE ||
                        Magic.cast(SpellBooks.Modern.WIND_STRIKE))) {

            InteractionUtil.walkToAndInteract(Npcs.getNearest((n) -> n.getInteractingEntity() == null && n.getName().equals("Chicken")),
                    "Cast", () -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_WIND_STRIKE_CHICKEN, 4000);
        }
    }

    private static void talk() {
        if (InteractionUtil.walkToLocatable(ENTRANCE_TILE, 7)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Magic Instructor"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

