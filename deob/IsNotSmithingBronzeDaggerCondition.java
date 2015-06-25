package deob;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

final class IsNotSmithingBronzeDaggerCondition implements Condition {

    private static final int S_SMITH_BRONZE_DAGGER = 350;

    @Override
    public boolean check() {
        if (Settings.get(281) != S_SMITH_BRONZE_DAGGER) {
            return true;
        }

        return false;
    }
}

