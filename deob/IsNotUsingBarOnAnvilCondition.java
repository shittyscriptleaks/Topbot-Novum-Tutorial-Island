package deob;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsNotUsingBarOnAnvilCondition implements Condition {

    private static final int S_USE_BAR_ON_ANVIL = 340;

    @Override
    public boolean check() {
        if (Settings.get(281) != S_USE_BAR_ON_ANVIL) {
            return true;
        }

        return false;
    }
}

