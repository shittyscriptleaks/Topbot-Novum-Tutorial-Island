package deob;

import org.tbot.methods.Npcs;
import org.tbot.methods.Settings;
import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.Tile;
import org.tbot.wrappers.WidgetChild;

public final class FinancialAdvisorStep {

    private FinancialAdvisorStep() { }

    private static final Tile ENTRANCE_TILE = new Tile(3127, 3124);

    public static void handle() {
        if (Settings.get(281) > 520) {
            WidgetChild widgetChild = Widgets.getWidget(345, 0);
            if (widgetChild != null && widgetChild.isVisible()) {
                Time.sleep(600, 1200);
                Walking.walkTileMM(new Tile(3123, 3123).getRandomized(1));
                return;
            }

            FinancialAdvisorStep.talk();
        }
    }

    public static boolean hasProgressedPast() {
        if (Settings.get(281) > 530) {
            return true;
        }
        return false;
    }

    private static void talk() {
        if (Util.walkToLocatable(ENTRANCE_TILE, 2)) {
            Util.walkToAndInteract(Npcs.getNearest("Financial Advisor"), "Talk-to", Util.CAN_CONTINUE_DIALOG_COND, 3000);
        }
    }

    public static void main(String[] args) {
        System.out.println(WebUtil.decryptStringStub("\u0007o?e~z<"));
    }
}

