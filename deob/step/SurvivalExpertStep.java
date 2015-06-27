package deob.step;

import deob.Constants;
import deob.util.InteractionUtil;
import org.tbot.methods.*;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.GameObject;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

public final class SurvivalExpertStep {

    private SurvivalExpertStep() { }

    private static final Tile ENTRANCE_TILE = new Tile(3102, 3096);

    private static final int S_TALK_TO_EXPERT = 20;
    private static final int S_OPEN_INVENTORY = 30;
    private static final int S_CHOP_TREE = 40;
    private static final int S_LIGHT_FIRE = 50;
    private static final int S_OPEN_SKILLS = 60;
    private static final int S_SKILLS_OPEN_TALK_TO_EXPERT = 70;
    private static final int S_FISH_SHRIMP = 80;
    private static final int S_COOK_SHRIMP = 90;
    private static final int S_BURNT_FISH_SHRIMP = 100;
    private static final int S_COOK_SHRIM_AGAIN = 110;

    private static void f() {
        if (Widgets.isOpen(11) || Widgets.openTab(11)) {
            WidgetChild widgetChild = Widgets.getWidget(261, 13);
            if (widgetChild != null && widgetChild.isVisible()) {
                WidgetChild widgetChild2;

                if (Settings.get(168) != 4 && (widgetChild2 = Widgets.getWidget(261, 14)) != null && widgetChild2.isVisible()) {
                    widgetChild2.click();
                }

                if (Settings.get(169) != 4 && (widgetChild2 = Widgets.getWidget(261, 20)) != null && widgetChild2.isVisible()) {
                    widgetChild2.click();
                }

                if (Settings.get(872) != 4 && (widgetChild2 = Widgets.getWidget(261, 26)) != null && widgetChild2.isVisible()) {
                    widgetChild2.click();
                }
            } else {
                WidgetChild widgetChild3 = Widgets.getWidget(261, 1);
                if (widgetChild3 != null && (widgetChild3 = widgetChild3.getChild(2)) != null && widgetChild3.isVisible()) {
                    widgetChild3.click();
                }
            }
        }
    }

    public static boolean hasProgressedPast() {
        return Settings.get(Constants.PROGRESS_SETTING_ID) > 110;

    }

    public static void handle() {
        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_TALK_TO_EXPERT || Settings.get(Constants.PROGRESS_SETTING_ID) == S_SKILLS_OPEN_TALK_TO_EXPERT) {
            if (Settings.get(168) != 4 || Settings.get(169) != 4 || Settings.get(872) != 4) {
                SurvivalExpertStep.f();
                return;
            }

            SurvivalExpertStep.talk();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_INVENTORY) {
            Widgets.openTab(3);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_CHOP_TREE) {
            SurvivalExpertStep.chopTree();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_LIGHT_FIRE) {
            SurvivalExpertStep.attemptLightFire();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_OPEN_SKILLS) {
            Widgets.openTab(1);
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_FISH_SHRIMP) {
            SurvivalExpertStep.fish();
        }

        if (Settings.get(Constants.PROGRESS_SETTING_ID) == S_COOK_SHRIMP || Settings.get(Constants.PROGRESS_SETTING_ID) == S_BURNT_FISH_SHRIMP || Settings.get(Constants.PROGRESS_SETTING_ID) == S_COOK_SHRIM_AGAIN) {
            SurvivalExpertStep.cookShrimp(Settings.get(Constants.PROGRESS_SETTING_ID));
        }
    }

    private static void talk() {
        if (InteractionUtil.walkToLocatable(ENTRANCE_TILE, 5)) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Survival Expert"), "Talk-to", Constants.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    private static void chopTree() {
        if (Players.getLocal().getAnimation() == -1) {
            InteractionUtil.walkToAndInteract(GameObjects.getNearest("Tree"), "Chop down", () -> Players.getLocal().getAnimation() != -1, 3000);
        }
    }

    private static void attemptLightFire() {
        if (Inventory.contains("Logs")) {
            if (Players.getLocal().getAnimation() == -1) {
                GameObject gameObject = GameObjects.getNearest("Fire");
                if (gameObject != null && gameObject.getLocation().distance() == 0) {
                    InteractionUtil.walkToLocatable(Players.getLocal().getLocation().getRandomized(3), 0);
                    return;
                }

                if (Inventory.useItemOn("Tinderbox", "Logs")) {
                    Time.sleepUntil(() -> Settings.get(Constants.PROGRESS_SETTING_ID) != S_LIGHT_FIRE, 8000);
                }
            }
        } else {
            SurvivalExpertStep.chopTree();
        }
    }

    private static void fish() {
        if (Players.getLocal().getAnimation() == -1) {
            InteractionUtil.walkToAndInteract(Npcs.getNearest("Fishing spot"), "Net", () -> Players.getLocal().getAnimation() != -1, 3000, false);
        }
    }

    private static void cookShrimp(int setting) {
        GameObject gameObject = GameObjects.getNearest("Fire");
        if (gameObject == null) {
            SurvivalExpertStep.attemptLightFire();
            return;
        }

        if (Inventory.contains("Raw shrimps")) {
            if (Inventory.useItemOn("Raw shrimps", gameObject)) {
                Time.sleepUntil(() -> Settings.get(Constants.PROGRESS_SETTING_ID) != setting, 6000);
            }
        } else {
            SurvivalExpertStep.fish();
        }
    }

}

