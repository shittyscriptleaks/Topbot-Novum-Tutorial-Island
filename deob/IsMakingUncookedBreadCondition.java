package deob;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

final class IsMakingUncookedBreadCondition implements Condition {

    @Override
    public boolean check() {
        if (Settings.get(281) != 150) {
            return true;
        }

        return false;
    }

}

