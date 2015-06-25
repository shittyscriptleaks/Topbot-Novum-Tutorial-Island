package deob.step;

import deob.RatNPCFilter;
import deob.util.Util;
import deob.condition.IsNotViewingWornEquipmentCondition;
import deob.condition.LocalPlayerHasNoInteractingEntityCondition;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Equipment;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

public final class CombatInstructorStep {

    private CombatInstructorStep() { }

    private static final Tile ENTRACE_TILE = new Tile(3107, 9509);

    private static final int S_WALK_THROUGH_GATE = 360;
    private static final int S_TALK_TO_INSTRUCTOR = 370;
    private static final int S_OPEN_EQUIP_TAB = 390;
    private static final int S_VIEW_WORN_EQUIPMENT = 400;
    private static final int S_WIELD_DAGGER = 405;
    private static final int S_EQUIPPED_TALK_TO_INSTRUCTOR = 410;
    private static final int S_EQUIP_SWORD_AND_SHIELD = 420;
    private static final int S_OPEN_COMBAT_OPTIONS = 430;
    private static final int S_ENTER_RAT_AREA = 440;
    private static final int S_KILL_RAT_WITH_MELEE = 450;
    private static final int S_IN_RAT_COMBAT = 460;
    private static final int S_DEAD_RAT_TALK_TO_INSTRUCTOR = 470;
    private static final int S_EQUIP_RANGED = 480;

    private static void killRat(boolean canBeFarAway) {
        Util.walkToAndInteract(Npcs.getNearest(new RatNPCFilter()), "Attack", new LocalPlayerHasNoInteractingEntityCondition(), 3000, !canBeFarAway);
    }

    public static void handle() {
        WidgetChild widgetChild;
        if (Settings.get(281) == S_WALK_THROUGH_GATE ||
                Settings.get(281) == S_TALK_TO_INSTRUCTOR ||
                Settings.get(281) == S_EQUIPPED_TALK_TO_INSTRUCTOR ||
                Settings.get(281) == S_DEAD_RAT_TALK_TO_INSTRUCTOR) {

            CombatInstructorStep.talk();
        }

        if (Settings.get(281) == S_OPEN_EQUIP_TAB) {
            Widgets.openTab(4);
        }

        if (Settings.get(281) == S_VIEW_WORN_EQUIPMENT && (Equipment.isOpen() || Equipment.openTab()) && (widgetChild = Widgets.getWidget(387, 17)) != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleepUntil(new IsNotViewingWornEquipmentCondition(), 2000);
        }

        if (Settings.get(281) == S_WIELD_DAGGER) {
            CombatInstructorStep.checkEquip("Bronze dagger");
        }

        if (Settings.get(281) == S_EQUIP_SWORD_AND_SHIELD) {
            CombatInstructorStep.checkEquip("Bronze sword", "Wooden shield");
        }

        if (Settings.get(281) == S_OPEN_COMBAT_OPTIONS) {
            Widgets.openTab(0);
        }

        if ((Settings.get(281) == S_ENTER_RAT_AREA || Settings.get(281) == S_KILL_RAT_WITH_MELEE || Settings.get(281) == S_IN_RAT_COMBAT) && Util.walkToLocatable(new Tile(3107, 9519), 3) && Players.getLocal().getInteractingEntity() == null) {
            CombatInstructorStep.killRat(false);
        }

        if (Settings.get(281) == S_EQUIP_RANGED && CombatInstructorStep.checkEquip("Shortbow", "Bronze arrow") && Util.walkToLocatable(new Tile(3108, 9512), 2)) {
            CombatInstructorStep.killRat(true);
        }
    }

    public static boolean checkEquip(String... itemNames) {
        int n;
        int len = itemNames.length;
        int ptr = n = 0;
        while (ptr < len) {
            String string = itemNames[n];
            if (!Equipment.contains(string)) {
                if (Inventory.contains(string) && Inventory.getFirst(string).click()) {
                    Time.sleep(100, 400);
                }
                return false;
            }
            ptr = ++n;
        }
        return true;
    }

    public static boolean hasProgressedPast() {
        if (Settings.get(281) > 490) {
            return true;
        }

        return false;
    }

    private static void talk() {
        if (Util.walkToLocatable(ENTRACE_TILE, 4)) {
            Util.walkToAndInteract(Npcs.getNearest("Combat Instructor"), "Talk-to", Util.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

}

