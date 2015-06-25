package deob;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsNotMakingAFireCondition implements Condition {

    private static final int S_MAKE_FIRE = 50;

    @Override
    public boolean check() {
        if (Settings.get(281) != S_MAKE_FIRE ) {
            return true;
        }
        return false;
    }

}

