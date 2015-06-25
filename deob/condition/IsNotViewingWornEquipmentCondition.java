package deob.condition;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsNotViewingWornEquipmentCondition implements Condition {

    private static final int S_VIEW_WORN_EQUIPMENT = 400;

    @Override
    public boolean check() {
        if (Settings.get(281) != S_VIEW_WORN_EQUIPMENT) {
            return true;
        }

        return false;
    }

}

