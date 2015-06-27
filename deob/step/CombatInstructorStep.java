package deob.step;

import deob.util.InteractionUtil;
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

    public static boolean hasProgressedPast() {
        return Settings.get(281) > 490;

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
            Time.sleepUntil(() -> Settings.get(281) != S_VIEW_WORN_EQUIPMENT, 2000);
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

        if ((Settings.get(281) == S_ENTER_RAT_AREA || Settings.get(281) == S_KILL_RAT_WITH_MELEE || Settings.get(281) == S_IN_RAT_COMBAT) && InteractionUtil.walkToLocatable(new Tile(3107, 9519), 3) && Players.getLocal().getInteractingEntity() == null) {
            CombatInstructorStep.killRat(false);
        }

        if (Settings.get(281) == S_EQUIP_RANGED && CombatInstructorStep.checkEquip("Shortbow", "Bronze arrow") && InteractionUtil.walkToLocatable(new Tile(3108, 9512), 2)) {
            CombatInstructorStep.killRat(true);
        }
    }

    private static void talk() {
        if (InteractionUtil.walkToLocatable(ENTRACE_TILE, 4)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Combat Instructor"), "Talk-to", InteractionUtil.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    public static boolean checkEquip(String... itemNames) {
        for (String s : itemNames) {
            if (!Equipment.contains(s)) {
                if (Inventory.contains(s) && Inventory.getFirst(s).click()) {
                    Time.sleep(100, 400);
                }
                return false;
            }
        }

        return true;
    }

    private static void killRat(boolean canBeFarAway) {
        InteractionUtil.walkToAndInteract(Npcs.getNearest((n) -> n.getName() != null && n.getInteractingEntity() == null && n.getName().equals("Giant rat")),
                "Attack", () -> Players.getLocal().getInteractingEntity() == null, 3000, !canBeFarAway);
    }

}

