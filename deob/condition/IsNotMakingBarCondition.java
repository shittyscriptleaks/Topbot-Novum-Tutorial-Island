package deob.condition;

import org.tbot.methods.Settings;
import org.tbot.util.Condition;

public final class IsNotMakingBarCondition  implements Condition {

    private static final int S_MAKE_BAR = 320;

    @Override
    public boolean check() {
        if (Settings.get(281) != S_MAKE_BAR) {
            return true;
        }

        return false;
    }

}

