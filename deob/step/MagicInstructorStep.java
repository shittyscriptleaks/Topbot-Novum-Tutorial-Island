package deob.step;

import deob.ChickenNPCFilter;
import deob.util.Util;
import deob.condition.IsNotCastingWindStrikeCondition;
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

    private static void talk() {
        if (Util.walkToLocatable(ENTRANCE_TILE, 7)) {
            Util.walkToAndInteract(Npcs.getNearest("Magic Instructor"), "Talk-to", Util.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    public static boolean hasProgressedPast() {
        if (Settings.get(281) >= 1000) {
            return true;
        }

        return false;
    }

    public static void handle() {
        if (Settings.get(281) == S_LEAVE_MONESTRY ||
                Settings.get(281) == S_TALK_TO_MAGIC_INSTRUCTOR ||
                Settings.get(281) == S_MAGIC_TAB_OPEN_TALK_TO_MAGIC_INSTRUCTOR ||
                Settings.get(281) == S_LEAVE_TALK_TO_MAGIC_INSTRUCTOR) {

            MagicInstructorStep.talk();
        }

        if (Settings.get(281) == S_OPEN_MAGIC_TAB) {
            Widgets.openTab(6);
        }

        if (Settings.get(281) == S_WIND_STRIKE_CHICKEN && Util.walkToLocatable(new Tile(3140, 3090), 0) && (Magic.getSelectedSpell() == SpellBooks.Modern.WIND_STRIKE || Magic.cast(SpellBooks.Modern.WIND_STRIKE))) {
            Util.walkToAndInteract(Npcs.getNearest(new ChickenNPCFilter()), "Cast", new IsNotCastingWindStrikeCondition(), 4000);
        }
    }
}

