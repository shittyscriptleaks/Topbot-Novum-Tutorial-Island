package deob.condition;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsNotMakingBreadCondition implements Condition {

    private static final int S_MAKE_BREAD = 160;

    @Override
    public boolean check() {
        if (Settings.get(281) != S_MAKE_BREAD) {
            return true;
        }

        return false;
    }

}

