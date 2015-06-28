package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Equipment;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

public final class CombatInstructorStep implements TutorialStep {

    private static final Tile ENTRACE_TILE = new Tile(3107, 9509);
    private static final Tile PIN_ENTRANCE_TILE = new Tile(3108, 9512);

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

    @Override
    public boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 490;

    }

    @Override
    public void handle() {
        WidgetChild widgetChild;
        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_WALK_THROUGH_GATE ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_TALK_TO_INSTRUCTOR ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_EQUIPPED_TALK_TO_INSTRUCTOR ||
                Settings.get(Constants.PROGRESS_SETTING_ID) == S_DEAD_RAT_TALK_TO_INSTRUCTOR) {

            talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_EQUIP_TAB) {
            Widgets.openTab(4);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_VIEW_WORN_EQUIPMENT && (Equipment.isOpen() || Equipment.openTab()) && (widgetChild = Widgets.getWidget(387, 17)) != null && widgetChild.isVisible() && widgetChild.click()) {
            Time.sleepUntil(() -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_VIEW_WORN_EQUIPMENT, 2000);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_WIELD_DAGGER) {
            checkEquip("Bronze dagger");
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_EQUIP_SWORD_AND_SHIELD) {
            checkEquip("Bronze sword", "Wooden shield");
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_COMBAT_OPTIONS) {
            Widgets.openTab(0);
        }

        if ((Settings.get(Constants.PROGRESS_SETTING_ID) == S_ENTER_RAT_AREA || Settings.get(Constants.PROGRESS_SETTING_ID) == S_KILL_RAT_WITH_MELEE || Settings.get(Constants.PROGRESS_SETTING_ID) == S_IN_RAT_COMBAT) && InteractionUtil.walkTo(new Tile(3107, 9519), 3) && Players.getLocal().getInteractingEntity() == null) {
            killRat(false);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_EQUIP_RANGED && checkEquip("Shortbow", "Bronze arrow") && InteractionUtil.walkTo(PIN_ENTRANCE_TILE, 2)) {
            killRat(true);
        }
    }

    private void talk() {
        if (InteractionUtil.walkTo(ENTRACE_TILE, 4)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Combat Instructor"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    public boolean checkEquip(String... itemNames) {
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

    private void killRat(boolean canBeFarAway) {
        InteractionUtil.walkToAndInteract(Npcs.getNearest((n) -> n.getName() != null && n.getInteractingEntity() == null && n.getName().equals("Giant rat")),
                "Attack", () -> Players.getLocal().getInteractingEntity() == null, 3000, !canBeFarAway);
    }

}

